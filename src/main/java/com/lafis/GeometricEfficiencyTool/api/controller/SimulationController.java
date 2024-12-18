package com.lafis.GeometricEfficiencyTool.api.controller;

import com.lafis.GeometricEfficiencyTool.api.request.*;
import com.lafis.GeometricEfficiencyTool.database.domain.aperture.CircularAperture;
import com.lafis.GeometricEfficiencyTool.database.domain.aperture.RectangularAperture;
import com.lafis.GeometricEfficiencyTool.database.domain.simulation.GeometricContext;
import com.lafis.GeometricEfficiencyTool.database.domain.simulation.Simulation;
import com.lafis.GeometricEfficiencyTool.database.domain.source.CuboidSource;
import com.lafis.GeometricEfficiencyTool.database.domain.source.CylindricalSource;
import com.lafis.GeometricEfficiencyTool.database.domain.source.Source;
import com.lafis.GeometricEfficiencyTool.database.domain.source.SphericalSource;
import com.lafis.GeometricEfficiencyTool.service.GeometricContextFactory;
import com.lafis.GeometricEfficiencyTool.service.SimulationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/simulation")
public class SimulationController {

    private final GeometricContextFactory factory;
    private final SimulationService service;

    @Autowired
    public SimulationController(GeometricContextFactory factory, SimulationService service) {
        this.factory = factory;
        this.service = service;
    }

    @PostMapping
    public boolean simulateContext(@RequestBody NewContextRequest request){
        GeometricContext context = factory.createContext(
                request.getAperture(),
                request.getSource(),
                request.getHeight()
        );
        Simulation simulation = new Simulation(context, request.getEmissions());

        service.execute(simulation);
        return true;
    }

    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public Simulation save(){
        return service.save();
    }

    @PatchMapping("/rectangular")
    @ResponseStatus(HttpStatus.OK)
    public Simulation setRectangularAperture(@RequestBody SetRectangularApertureRequest request){
        RectangularAperture aperture = new RectangularAperture(request.height(), request.width(), request.apertureHeight());
        return service.setAperture(request.simulationId(), aperture);
    }

    @PatchMapping("/circular")
    @ResponseStatus(HttpStatus.OK)
    public Simulation setCircularAperture(@RequestBody SetCircularApertureRequest request){
        CircularAperture aperture = new CircularAperture(request.radius(), request.height());
        return service.setAperture(request.simulationId(), aperture);
    }

    @PatchMapping("/source/cylindrical")
    @ResponseStatus(HttpStatus.OK)
    public Simulation setCylindricalSource(@RequestBody SetCylindricalSourceRequest request){
        Source source = new CylindricalSource(
                request.initialHeight(),
                request.finalHeight(),
                request.increment(),
                request.sourceHeight(),
                request.sourceRadius()
        );
        return service.setSource(request.simulationId(), source);
    }

    @PatchMapping("/source/cuboid")
    @ResponseStatus(HttpStatus.OK)
    public Simulation setCuboidSource(@RequestBody SetCuboidSourceRequest request){
        Source source = new CuboidSource(
                request.sourceHeight(),
                request.sourceWidth(),
                request.initialHeight(),
                request.sourceWidth(),
                request.increment()
        );
        return service.setSource(request.simulationId(), source);
    }

    @PatchMapping("/source/spherical")
    public Simulation setSphericalSource(@RequestBody SetSphericalSourceRequest request){
        Source source = new SphericalSource(
                request.sourceRadius(),
                request.initialHeight(),
                request.finalHeight(),
                request.increment()
        );
        return service.setSource(request.simulationId(), source);
    }
}
