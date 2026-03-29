package com.lafis.GeometricEfficiencyTool.api.controller;

import com.lafis.GeometricEfficiencyTool.api.request.*;
import com.lafis.GeometricEfficiencyTool.api.response.SimulationResponse;
import com.lafis.GeometricEfficiencyTool.database.domain.aperture.CircularAperture;
import com.lafis.GeometricEfficiencyTool.database.domain.aperture.RectangularAperture;
import com.lafis.GeometricEfficiencyTool.database.domain.simulation.ApertureType;
import com.lafis.GeometricEfficiencyTool.database.domain.simulation.GeometricContext;
import com.lafis.GeometricEfficiencyTool.database.domain.simulation.Simulation;
import com.lafis.GeometricEfficiencyTool.database.domain.simulation.SourceType;
import com.lafis.GeometricEfficiencyTool.database.domain.source.CuboidSource;
import com.lafis.GeometricEfficiencyTool.database.domain.source.CylindricalSource;
import com.lafis.GeometricEfficiencyTool.database.domain.source.PointSource;
import com.lafis.GeometricEfficiencyTool.database.domain.source.Source;
import com.lafis.GeometricEfficiencyTool.database.domain.source.SphericalSource;
import com.lafis.GeometricEfficiencyTool.database.domain.user.User;
import com.lafis.GeometricEfficiencyTool.service.AuthorizationService;
import com.lafis.GeometricEfficiencyTool.service.SimulationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/simulation")
public class SimulationController {
    private final SimulationService service;
    private CylindricalSource cylindricalSource;
    private AuthorizationService authorizationService;

    @PostMapping("/start")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<?> startSimulation(@RequestParam String simulationId){
        Simulation simulation = this.service.findById(simulationId);
        if (simulation == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        CompletableFuture<Boolean> future = this.service.execute(simulation);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Simulação iniciada com sucesso.");
    }

    @Autowired
    public SimulationController(SimulationService service, CylindricalSource cylindricalSource, AuthorizationService authorizationService) {
        this.service = service;
        this.cylindricalSource = cylindricalSource;
        this.authorizationService = authorizationService;
    }

    @GetMapping("/id")
    public ResponseEntity<SimulationResponse> findById(@RequestParam String simulationId){
        Simulation simulation = this.service.findById(simulationId);
        if (simulation == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(toResponse(simulation));
    }


    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public Simulation save(@RequestBody CreateNewSimulationRequest request){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String login = authentication.getName();
        User user = (User) authorizationService.loadUserByUsername(login);
        return service.save(request.emissions(), request.sourceHeight(), user.getId());
    }

    @PostMapping("/new-context")
    @ResponseStatus(HttpStatus.CREATED)
    public Simulation create(@RequestBody CreateNewContextRequest request){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String login = authentication.getName();
        User user = (User) authorizationService.loadUserByUsername(login);

        GeometricContext context = new GeometricContext(request.aperture(), request.source());
        Simulation simulation = new Simulation(context, request.emissions(), request.sourceHeight(),user.getId());
        return this.service.save(simulation);
    }

    @PatchMapping("/rectangular")
    @ResponseStatus(HttpStatus.OK)
    public Simulation setRectangularAperture(@RequestBody SetRectangularApertureRequest request){
        RectangularAperture aperture = new RectangularAperture(request.depth(), request.width(), request.height());
        return service.setAperture(request.simulationId(), aperture, ApertureType.RECTANGULAR);
    }

    @PatchMapping("/circular")
    @ResponseStatus(HttpStatus.OK)
    public Simulation setCircularAperture(@RequestBody SetCircularApertureRequest request){
        CircularAperture aperture = new CircularAperture(request.radius(), request.height());
        return service.setAperture(request.simulationId(), aperture, ApertureType.CIRCULAR);
    }

    @PatchMapping("/source/cylindrical")
    @ResponseStatus(HttpStatus.OK)
    public Simulation setCylindricalSource(@RequestBody SetCylindricalSourceRequest request){
        cylindricalSource.setHeight(request.sourceHeight());
        cylindricalSource.setRadius(request.sourceRadius());
        cylindricalSource.setCenterX(request.centerX());
        cylindricalSource.setCenterY(request.centerY());
        cylindricalSource.setCenterZ(request.centerZ());
        return service.setSource(request.simulationId(), cylindricalSource, SourceType.CYLINDRICAL);
    }

    @PatchMapping("/source/cuboid")
    @ResponseStatus(HttpStatus.OK)
    public Simulation setCuboidSource(@RequestBody SetCuboidSourceRequest request){
        Source source = new CuboidSource(
                request.sourceHeight(),
                request.sourceWidth(),
                request.sourceDepth(),
                request.centerX(),
                request.centerY(),
                request.centerZ()
        );
        return service.setSource(request.simulationId(), source, SourceType.CUBOID);
    }

    @PatchMapping("/source/spherical")
    public Simulation setSphericalSource(@RequestBody SetSphericalSourceRequest request){
        Source source = new SphericalSource(
                request.sourceRadius(),
                request.centerX(),
                request.centerY(),
                request.centerZ()
        );
        return service.setSource(request.simulationId(), source, SourceType.SPHERICAL);
    }

    @PatchMapping("/source/point")
    @ResponseStatus(HttpStatus.OK)
    public Simulation setPointSource(@RequestBody SetPointSourceRequest request){
        Source source = new PointSource(
                request.centerX(),
                request.centerY(),
                request.centerZ()
        );
        return service.setSource(request.simulationId(), source, SourceType.POINT);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<SimulationResponse> findAll(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String login = authentication.getName();
        User user = (User) authorizationService.loadUserByUsername(login);
        return service.findAll(user.getId())
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @GetMapping("/running")
    public List<SimulationResponse> findRunningSimulations(){
        return service.findRunning()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    private SimulationResponse toResponse(Simulation simulation) {
        double solidAngle = service.calculateSolidAngle(simulation);
        double solidAngleError = service.calculateSolidAngleError(simulation);

        return new SimulationResponse(
                simulation.getId(),
                simulation.getContext(),
                simulation.getEmissions(),
                simulation.getSourceHeight(),
                simulation.getEscaped(),
                simulation.getDuration(),
                simulation.getUserId(),
                simulation.getCreated(),
                simulation.getModified(),
                simulation.getStatus(),
                simulation.getApertureType(),
                simulation.getSourceType(),
                solidAngle,
                solidAngleError
        );
    }
}
