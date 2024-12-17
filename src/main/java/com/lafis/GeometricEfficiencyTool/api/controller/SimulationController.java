package com.lafis.GeometricEfficiencyTool.api.controller;

import com.lafis.GeometricEfficiencyTool.api.request.NewContextRequest;
import com.lafis.GeometricEfficiencyTool.api.request.SetCircularApertureRequest;
import com.lafis.GeometricEfficiencyTool.api.request.SetRectangularApertureRequest;
import com.lafis.GeometricEfficiencyTool.database.domain.aperture.CircularAperture;
import com.lafis.GeometricEfficiencyTool.database.domain.aperture.RectangularAperture;
import com.lafis.GeometricEfficiencyTool.database.domain.simulation.GeometricContext;
import com.lafis.GeometricEfficiencyTool.database.domain.simulation.Simulation;
import com.lafis.GeometricEfficiencyTool.service.GeometricContextFactory;
import com.lafis.GeometricEfficiencyTool.service.SimulationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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
    public ResponseEntity<Simulation> save(){
        return ResponseEntity.of(Optional.of(service.save()));
    }

    @PatchMapping("/rectangular")
    public ResponseEntity<Simulation> setRectangularAperture(@RequestBody SetRectangularApertureRequest request){
        RectangularAperture aperture = new RectangularAperture(request.height(), request.width(), request.apertureHeight());
        return ResponseEntity.status(HttpStatus.CREATED).body(service.setAperture(request.simulationId(), aperture));
    }

    @PatchMapping("/circular")
    public ResponseEntity<Simulation> setCircularAperture(@RequestBody SetCircularApertureRequest request){
        CircularAperture aperture = new CircularAperture(request.radius(), request.height());
        return ResponseEntity.status(HttpStatus.CREATED).body(service.setAperture(request.simulationId(), aperture));
    }
}
