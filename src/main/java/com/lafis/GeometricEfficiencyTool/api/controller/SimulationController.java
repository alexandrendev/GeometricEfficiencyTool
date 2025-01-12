package com.lafis.GeometricEfficiencyTool.api.controller;

import com.lafis.GeometricEfficiencyTool.api.request.*;
import com.lafis.GeometricEfficiencyTool.database.domain.aperture.CircularAperture;
import com.lafis.GeometricEfficiencyTool.database.domain.aperture.RectangularAperture;
import com.lafis.GeometricEfficiencyTool.database.domain.simulation.ApertureType;
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

    @Autowired
    public SimulationController(SimulationService service) {
        this.service = service;
    }

    @PostMapping("/start")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Simulation> startSimulation(@RequestBody String simulationId){
        Simulation simulation = this.service.findById(simulationId);
//        this.service.execute(simulation);
        return ResponseEntity.ok(simulation);
    }

    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public Simulation save(@RequestBody CreateNewSimulationRequest request){
        return service.save(request.emissions(), request.increment(), request.finalHeight());
    }

    @PatchMapping("/rectangular")
    @ResponseStatus(HttpStatus.OK)
    public Simulation setRectangularAperture(@RequestBody SetRectangularApertureRequest request){
        RectangularAperture aperture = new RectangularAperture(request.height(), request.width(), request.apertureHeight());
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
        Source source = new CylindricalSource(
                request.sourceHeight(),
                request.sourceRadius()
        );
        return service.setSource(request.simulationId(), source, SourceType.CYLINDRICAL);
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
}
