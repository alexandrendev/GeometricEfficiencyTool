package com.lafis.GeometricEfficiencyTool.api.controller;

import com.lafis.GeometricEfficiencyTool.api.request.RequestPasswordResetRequest;
import com.lafis.GeometricEfficiencyTool.api.request.ResetPasswordRequest;
import com.lafis.GeometricEfficiencyTool.database.domain.user.ResetToken;
import com.lafis.GeometricEfficiencyTool.database.domain.user.User;
import com.lafis.GeometricEfficiencyTool.database.repository.user.ResetTokenRepository;
import com.lafis.GeometricEfficiencyTool.database.repository.user.UserRepository;
import com.lafis.GeometricEfficiencyTool.service.EmailService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailSendException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.LocalDate;

@RestController
@RequestMapping("/password/reset")
public class ResetPasswordController {
    private final UserRepository userRepository;
    private final EmailService emailService;
    private final ResetTokenRepository resetTokenRepository;

    @Value("${app.base.url}")
    private String baseURL;


    @PostMapping
    public ResponseEntity resetPassword(@RequestBody ResetPasswordRequest req) {
        ResetToken resetToken = resetTokenRepository.findById(req.token()).orElse(null);

        if (resetToken == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        if (resetToken.getExpiresAt().isBefore(Instant.now())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        if (resetToken.getIsUsed()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Token já utilizado");
        }

        User user = userRepository.findById(resetToken.getUserId()).get();

        if (user == null) {
            return ResponseEntity.badRequest().build();
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(req.password());

        user.setPassword(encryptedPassword);
        userRepository.save(user);
        return ResponseEntity.ok().build();
    }




    @PostMapping("/request")
    public ResponseEntity requestPasswordReset(@RequestBody RequestPasswordResetRequest req) throws MailSendException {
        User user = userRepository.findByLogin(req.email());
        if (user == null) return ResponseEntity.badRequest().build();

        ResetToken token = ResetToken.builder()
                .userId(user.getId())
                .createdAt(Instant.now())
                .expiresAt(Instant.now().plusSeconds(3600))
                .isUsed(false)
        .build();

        resetTokenRepository.save(token);
        System.out.println(resetTokenRepository.findById(token.getId()));

        System.out.println(token.getId());

        String link = new StringBuilder()
                .append(this.baseURL)
                .append("/password/reset/")
                .append(token.getId())
                .toString();

        emailService.send(req.email(), "Redefinição de Senha - Geometric Efficiency Tool", link);
        return ResponseEntity.ok().build();

    }

    @Autowired
    public ResetPasswordController(UserRepository userRepository, EmailService emailService, ResetTokenRepository resetTokenRepository) {
        this.userRepository = userRepository;
        this.emailService = emailService;
        this.resetTokenRepository = resetTokenRepository;
    }
}
