# Cenarios de Uso e Teste - LAFIS / Geometric Efficiency Tool

Este documento descreve cenarios funcionais para validar o sistema LAFIS, composto por frontend Angular e backend Spring Boot. Os cenarios cobrem autenticacao, criacao de simulacoes, execucao, acompanhamento e consulta de relatorios.

## Atores

- Usuario pesquisador: pessoa que acessa o sistema para criar, executar e analisar simulacoes de eficiencia geometrica.
- Sistema LAFIS: frontend responsavel pela interface, visualizacao 3D e navegacao.
- API Geometric Efficiency Tool: backend responsavel por autenticacao, persistencia, execucao da simulacao e calculo dos resultados.

## Cenario 1 - Cadastro de Novo Usuario

Objetivo: permitir que um novo usuario crie uma conta para acessar o sistema.

Pre-condicoes:

- O usuario nao possui conta cadastrada com o e-mail informado.
- O backend esta disponivel.

Fluxo principal:

1. O usuario acessa a tela de cadastro.
2. O usuario informa e-mail, senha e confirmacao de senha.
3. O sistema valida se o e-mail possui formato valido.
4. O sistema valida se a senha possui no minimo 8 caracteres.
5. O sistema valida se a senha e a confirmacao sao iguais.
6. O usuario confirma o cadastro.
7. O frontend envia os dados para `POST /auth/register`.
8. O backend criptografa a senha e salva o novo usuario.
9. O sistema exibe mensagem de sucesso e redireciona para a tela de login.

Resultado esperado:

- A conta e criada com sucesso.
- O usuario consegue acessar a tela de login.

Fluxos alternativos:

- E-mail ja cadastrado: o backend retorna conflito e o frontend exibe a mensagem `Este e-mail ja esta cadastrado.`.
- Senha e confirmacao diferentes: o formulario permanece invalido e o cadastro nao e enviado.
- Senha com menos de 8 caracteres: o formulario permanece invalido.

## Cenario 2 - Login de Usuario

Objetivo: permitir que um usuario autenticado acesse as funcionalidades protegidas.

Pre-condicoes:

- O usuario possui conta cadastrada.
- O backend esta disponivel.

Fluxo principal:

1. O usuario acessa a tela de login.
2. O usuario informa e-mail e senha.
3. O usuario confirma o login.
4. O frontend envia os dados para `POST /auth/login`.
5. O backend valida as credenciais e gera um token JWT.
6. O frontend salva o token no `localStorage`.
7. O usuario e redirecionado para `/home` ou para a rota protegida originalmente solicitada.

Resultado esperado:

- O usuario autenticado acessa as telas protegidas do sistema.

Fluxos alternativos:

- Credenciais invalidas: o sistema exibe mensagem de e-mail ou senha invalidos.
- Backend indisponivel: o sistema exibe mensagem informando que nao foi possivel conectar ao servidor.
- Token expirado: o guard de autenticacao bloqueia a rota protegida e exige novo login.

## Cenario 3 - Criacao de Simulacao com Abertura Circular e Fonte Pontual

Objetivo: criar uma simulacao simples usando uma abertura circular e uma fonte pontual.

Pre-condicoes:

- O usuario esta autenticado.
- O usuario esta na tela `Nova Simulacao`.

Dados de entrada sugeridos:

- Quantidade de emissoes: `10000`.
- Tipo de abertura: `Circular`.
- Altura do poco: `10` cm.
- Raio da abertura: `2` cm.
- Tipo de fonte: `Pontual`.
- Centro X: `0` cm.
- Centro Y: `0` cm.
- Centro Z: `0` cm.

Fluxo principal:

1. O usuario informa a quantidade de emissoes.
2. O usuario seleciona abertura circular.
3. O sistema exibe apenas os campos relacionados a abertura circular.
4. O usuario informa altura do poco e raio da abertura.
5. O usuario seleciona fonte pontual.
6. O sistema exibe apenas os campos de coordenadas da fonte.
7. O usuario informa as coordenadas X, Y e Z.
8. A pre-visualizacao 3D e atualizada com a geometria informada.
9. O usuario envia o formulario.
10. O frontend envia a requisicao para `POST /simulation/new-context`.
11. O backend associa a simulacao ao usuario autenticado e salva o contexto geometrico.
12. O sistema exibe mensagem de sucesso e redireciona para a listagem de simulacoes.

Resultado esperado:

- A simulacao e criada com status inicial e aparece na listagem do usuario.
- O contexto salvo contem abertura circular e fonte pontual.

Fluxos alternativos:

- Quantidade de emissoes menor que 1: o formulario fica invalido.
- Raio igual ou menor que 0: o formulario exibe erro de valor positivo.
- Campo obrigatorio vazio: o formulario impede o envio.

## Cenario 4 - Criacao de Simulacao com Abertura Retangular e Fonte Cilindrica

Objetivo: validar uma configuracao mais completa, com dimensoes de abertura e fonte volumetrica.

Pre-condicoes:

- O usuario esta autenticado.
- O usuario esta na tela `Nova Simulacao`.

Dados de entrada sugeridos:

- Quantidade de emissoes: `50000`.
- Tipo de abertura: `Retangular`.
- Altura do poco: `15` cm.
- Largura no eixo X: `4` cm.
- Largura no eixo Y: `3` cm.
- Tipo de fonte: `Cilindrica`.
- Centro X: `0` cm.
- Centro Y: `0` cm.
- Centro Z: `2` cm.
- Altura da fonte: `5` cm.
- Raio da fonte: `1` cm.

Fluxo principal:

1. O usuario seleciona abertura retangular.
2. O sistema solicita largura no eixo X, largura no eixo Y e altura do poco.
3. O usuario seleciona fonte cilindrica.
4. O sistema solicita coordenadas do centro, altura e raio da fonte.
5. O sistema atualiza a pre-visualizacao 3D conforme os dados preenchidos.
6. O usuario envia o formulario.
7. O frontend monta o contexto com `apertureType = RECTANGULAR` e `sourceType = CYLINDRICAL`.
8. O backend salva a simulacao vinculada ao usuario.

Resultado esperado:

- A simulacao e criada com abertura retangular e fonte cilindrica.
- A listagem mostra a nova simulacao com os tipos correspondentes.

Fluxos alternativos:

- Dimensoes negativas ou zero: o sistema impede envio e mostra erro de valor positivo.
- Valores decimais com virgula ou ponto: o sistema deve aceitar ambos os formatos.

## Cenario 5 - Listagem de Simulacoes do Usuario

Objetivo: permitir que o usuario visualize as simulacoes criadas por ele.

Pre-condicoes:

- O usuario esta autenticado.
- Existe ao menos uma simulacao cadastrada para esse usuario.

Fluxo principal:

1. O usuario acessa a rota `/all`.
2. O frontend consulta `GET /simulation/all` enviando o token JWT.
3. O backend identifica o usuario autenticado.
4. O backend retorna apenas as simulacoes vinculadas ao usuario.
5. O frontend exibe os cards de simulacao.
6. A lista e atualizada periodicamente a cada 60 segundos.

Resultado esperado:

- O usuario visualiza apenas suas proprias simulacoes.
- As simulacoes mais recentes aparecem primeiro.

Fluxos alternativos:

- Usuario sem simulacoes: a lista aparece vazia ou o sistema pode orientar a criacao de uma nova simulacao.
- Token ausente ou invalido: o acesso e bloqueado pelo guard/interceptor e o usuario deve fazer login novamente.

## Cenario 6 - Execucao de Simulacao

Objetivo: iniciar o processamento de uma simulacao previamente criada.

Pre-condicoes:

- O usuario esta autenticado.
- A simulacao ja foi criada e possui contexto geometrico completo.
- A simulacao ainda nao foi finalizada.

Fluxo principal:

1. O usuario acessa a listagem de simulacoes.
2. O usuario seleciona a acao de iniciar a simulacao.
3. O frontend envia `POST /simulation/start?simulationId={id}`.
4. O backend busca a simulacao pelo identificador.
5. O backend altera o status para `RUNNING`.
6. O backend executa as emissoes de forma assincrona.
7. Para cada emissao, o sistema sorteia um ponto de emissao e uma direcao.
8. O sistema verifica se a emissao escapou pela abertura.
9. Ao final, o backend calcula duracao, total de emissoes escapadas e altera o status para `FINISHED`.

Resultado esperado:

- A simulacao passa por status de execucao e termina como finalizada.
- O resultado fica disponivel para consulta no relatorio.

Fluxos alternativos:

- Simulacao inexistente: o backend retorna `404 NOT FOUND`.
- Usuario tenta iniciar a mesma simulacao enquanto ela ja esta carregando no front: o sistema informa que a simulacao ja esta em andamento.
- Simulacao ja finalizada: o backend nao executa novamente.

## Cenario 7 - Consulta de Relatorio da Simulacao

Objetivo: permitir que o usuario analise os resultados de uma simulacao.

Pre-condicoes:

- O usuario esta autenticado.
- Existe uma simulacao cadastrada.

Fluxo principal:

1. O usuario acessa `/report/{id}` a partir da listagem.
2. O frontend consulta `GET /simulation/id?simulationId={id}`.
3. O backend retorna dados da simulacao e calcula angulo solido e erro do angulo solido.
4. O frontend exibe dados gerais da simulacao.
5. O frontend exibe grafico com emissoes escapadas e nao escapadas.
6. O frontend calcula percentual de escape e emissoes por segundo.
7. O frontend renderiza a geometria da simulacao em 3D.
8. O usuario pode recentralizar a camera ou imprimir o relatorio.

Resultado esperado:

- O relatorio apresenta dados de entrada, resultados numericos, grafico e visualizacao 3D.

Fluxos alternativos:

- ID inexistente: o backend retorna `404 NOT FOUND` e o front registra erro ao carregar simulacao.
- Duracao em formato inesperado: o calculo de emissoes por segundo pode falhar, pois o frontend espera o formato `X min Y sec Z ms`.

## Cenario 8 - Validacao de Acesso a Rotas Protegidas

Objetivo: garantir que apenas usuarios autenticados acessem funcionalidades internas.

Pre-condicoes:

- O usuario nao esta autenticado ou possui token expirado.

Fluxo principal:

1. O usuario tenta acessar diretamente uma rota protegida, como `/home`, `/new`, `/all`, `/report/{id}`, `/account` ou `/help`.
2. O guard de autenticacao verifica o token salvo no navegador.
3. Caso o token esteja ausente ou expirado, o acesso e bloqueado.
4. O usuario e redirecionado para login.

Resultado esperado:

- O sistema protege as funcionalidades internas contra acesso nao autenticado.

Fluxos alternativos:

- Usuario autenticado tenta acessar `/login` ou `/register`: o guard de nao autenticado deve impedir acesso desnecessario a essas telas.

## Cenario 9 - Recuperacao de Senha

Objetivo: permitir que um usuario solicite redefinicao de senha.

Pre-condicoes:

- O usuario possui e-mail cadastrado.
- O servico de e-mail do backend esta configurado.

Fluxo principal esperado:

1. O usuario acessa a tela de recuperacao de senha.
2. O usuario informa o e-mail cadastrado.
3. O frontend envia a solicitacao para `POST /password/reset/request`.
4. O backend cria um token de redefinicao com validade de 1 hora.
5. O backend envia um link de redefinicao para o e-mail do usuario.
6. O usuario acessa o link e informa uma nova senha.
7. O backend valida o token e atualiza a senha criptografada.

Resultado esperado:

- O usuario consegue redefinir a senha dentro do prazo de validade do token.

Observacao:

- No frontend atual, o envio da recuperacao esta comentado na tela de `ForgotPasswordComponent`. Portanto, este cenario existe no backend, mas ainda precisa ser integrado completamente no frontend.

Fluxos alternativos:

- E-mail inexistente: o backend retorna erro de requisicao.
- Token expirado: o backend rejeita a redefinicao.
- Token ja utilizado: o backend rejeita a redefinicao.

## Cenario 10 - Validacao Cientifica Basica por Aumento de Emissoes

Objetivo: observar a estabilidade estatistica do resultado conforme o numero de emissoes aumenta.

Pre-condicoes:

- O usuario esta autenticado.
- A mesma geometria sera usada em mais de uma simulacao.

Dados sugeridos:

- Abertura circular com raio `2` cm e altura do poco `10` cm.
- Fonte pontual centralizada em `(0, 0, 0)`.
- Rodada 1: `1000` emissoes.
- Rodada 2: `10000` emissoes.
- Rodada 3: `100000` emissoes.

Fluxo principal:

1. O usuario cria tres simulacoes com a mesma geometria e diferentes quantidades de emissoes.
2. O usuario executa as tres simulacoes.
3. O usuario consulta o relatorio de cada uma.
4. O usuario compara angulo solido, erro do angulo solido e percentual de escape.

Resultado esperado:

- Simulacoes com maior quantidade de emissoes tendem a apresentar menor erro estatistico.
- O tempo de execucao tende a aumentar conforme o numero de emissoes.

## Cenario 11 - Comparacao entre Tipos de Fonte

Objetivo: avaliar o impacto do tipo de fonte nos resultados da simulacao.

Pre-condicoes:

- O usuario esta autenticado.
- A abertura e mantida igual em todas as simulacoes.

Dados sugeridos:

- Quantidade de emissoes: `50000`.
- Abertura circular com raio `2` cm e altura do poco `10` cm.
- Simulacao A: fonte pontual em `(0, 0, 0)`.
- Simulacao B: fonte esferica com raio `1` cm em `(0, 0, 0)`.
- Simulacao C: fonte cilindrica com raio `1` cm, altura `2` cm em `(0, 0, 0)`.
- Simulacao D: fonte prismatica com dimensoes `2 x 2 x 2` cm em `(0, 0, 0)`.

Fluxo principal:

1. O usuario cria simulacoes mantendo a mesma abertura.
2. O usuario altera apenas o tipo e as dimensoes da fonte.
3. O usuario executa todas as simulacoes.
4. O usuario compara os relatorios gerados.

Resultado esperado:

- O sistema permite comparar como a distribuicao espacial da fonte afeta o percentual de escape e o angulo solido estimado.

## Cenario 12 - Comparacao entre Abertura Circular e Retangular

Objetivo: comparar os efeitos do formato da abertura na eficiencia geometrica.

Pre-condicoes:

- O usuario esta autenticado.
- A fonte e mantida igual nas simulacoes.

Dados sugeridos:

- Quantidade de emissoes: `50000`.
- Fonte pontual em `(0, 0, 0)`.
- Simulacao A: abertura circular com raio `2` cm e altura do poco `10` cm.
- Simulacao B: abertura retangular com eixo X `4` cm, eixo Y `4` cm e altura do poco `10` cm.

Fluxo principal:

1. O usuario cria duas simulacoes com a mesma fonte.
2. O usuario configura formatos diferentes de abertura.
3. O usuario executa as duas simulacoes.
4. O usuario compara angulo solido, erro e percentual de escape.

Resultado esperado:

- O sistema permite analisar como o formato e a area da abertura influenciam a eficiencia geometrica.

## Pontos de Atencao para Apresentacao ao Orientador

- O sistema ja possui fluxo principal de autenticacao, cadastro de simulacao, execucao assincrona, listagem e relatorio.
- A recuperacao de senha esta implementada no backend, mas a tela do frontend ainda nao envia a requisicao.
- Os cenarios cientificos mais interessantes sao os de comparacao: variacao do numero de emissoes, variacao do tipo de fonte e variacao do tipo de abertura.
- Para validacao, e recomendavel registrar os parametros de entrada e comparar os resultados gerados no relatorio: emissoes, escapadas, percentual de escape, angulo solido, erro e duracao.
