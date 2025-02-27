package com.lafis.GeometricEfficiencyTool.api.controller;

import com.lafis.GeometricEfficiencyTool.api.request.*;
import com.lafis.GeometricEfficiencyTool.database.domain.aperture.CircularAperture;
import com.lafis.GeometricEfficiencyTool.database.domain.aperture.RectangularAperture;
import com.lafis.GeometricEfficiencyTool.database.domain.simulation.ApertureType;
import com.lafis.GeometricEfficiencyTool.database.domain.simulation.GeometricContext;
import com.lafis.GeometricEfficiencyTool.database.domain.simulation.Simulation;
import com.lafis.GeometricEfficiencyTool.database.domain.simulation.SourceType;
import com.lafis.GeometricEfficiencyTool.database.domain.source.CuboidSource;
import com.lafis.GeometricEfficiencyTool.database.domain.source.CylindricalSource;
import com.lafis.GeometricEfficiencyTool.database.domain.source.Source;
import com.lafis.GeometricEfficiencyTool.database.domain.source.SphericalSource;
import com.lafis.GeometricEfficiencyTool.service.SimulationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/simulation")
public class SimulationController {
    private final SimulationService service;
    private CylindricalSource cylindricalSource;
    private SphericalSource sphericalSource;
    private CuboidSource cuboidSource;

    @PostMapping("/start")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> startSimulation(@RequestParam String simulationId){
        Simulation simulation = this.service.findById(simulationId);
        if(simulation == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        boolean response = this.service.execute(simulation);

        if(response) return ResponseEntity.ok(simulation);

        return ResponseEntity.status(HttpStatus.CONFLICT).body("A simulação já foi executada");
    }

    @Autowired
    public SimulationController(SimulationService service, CylindricalSource cylindricalSource) {
        this.service = service;
        this.cylindricalSource = cylindricalSource;
    }

    @GetMapping("/id")
    public ResponseEntity<Simulation> findById(@RequestParam String simulationId){
        Simulation simulation = this.service.findById(simulationId);
        return ResponseEntity.ok(simulation);
    }


    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public Simulation save(@RequestBody CreateNewSimulationRequest request){
        return service.save(request.emissions(), request.sourceHeight());
    }

    @PostMapping("/new-context")
    @ResponseStatus(HttpStatus.CREATED)
    public Simulation create(@RequestBody CreateNewContextRequest request){
        System.out.println(request);
        GeometricContext context = new GeometricContext(request.aperture(), request.source(), request.sourceHeight());
        Simulation simulation = new Simulation(context, request.emissions());
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
        return service.setSource(request.simulationId(), cylindricalSource, SourceType.CYLINDRICAL);
    }

    @PatchMapping("/source/cuboid")
    @ResponseStatus(HttpStatus.OK)
    public Simulation setCuboidSource(@RequestBody SetCuboidSourceRequest request){
        Source source = new CuboidSource(
                request.sourceHeight(),
                request.sourceWidth(),
                request.sourceWidth()
        );
        return service.setSource(request.simulationId(), source, SourceType.CUBOID);
    }

    @PatchMapping("/source/spherical")
    public Simulation setSphericalSource(@RequestBody SetSphericalSourceRequest request){
        Source source = new SphericalSource(
                request.sourceRadius()
        );
        return service.setSource(request.simulationId(), source, SourceType.SPHERICAL);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<Simulation> findAll(){
        return service.findAll();
    }

    @GetMapping("/running")
    public List<Simulation> findRunningSimulations(){
        return service.findRunning();
    }
}
