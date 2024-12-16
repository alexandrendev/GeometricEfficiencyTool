package com.lafis.GeometricEfficiencyTool.api.controller;

import com.lafis.GeometricEfficiencyTool.api.request.NewContextRequest;
import com.lafis.GeometricEfficiencyTool.api.request.SetRectangularApertureRequest;
import com.lafis.GeometricEfficiencyTool.database.domain.aperture.RectangularAperture;
import com.lafis.GeometricEfficiencyTool.database.domain.simulation.GeometricContext;
import com.lafis.GeometricEfficiencyTool.database.domain.simulation.Simulation;
import com.lafis.GeometricEfficiencyTool.service.GeometricContextFactory;
import com.lafis.GeometricEfficiencyTool.service.SimulationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    public ResponseEntity<Simulation> setRectangularAperture(@RequestBody SetRectangularApertureRequest request){
        RectangularAperture aperture = new RectangularAperture(request.apertureHeight(), request.apertureWidth());
        return ResponseEntity.ok().body(service.setAperture(request.simulationId(), aperture));
    }
}
