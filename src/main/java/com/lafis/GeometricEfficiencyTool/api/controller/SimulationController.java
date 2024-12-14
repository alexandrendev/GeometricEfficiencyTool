package com.lafis.GeometricEfficiencyTool.api.controller;

import com.lafis.GeometricEfficiencyTool.api.request.NewContextRequest;
import com.lafis.GeometricEfficiencyTool.domain.GeometricContext;
import com.lafis.GeometricEfficiencyTool.domain.Simulation;
import com.lafis.GeometricEfficiencyTool.domain.source.Source;
import com.lafis.GeometricEfficiencyTool.service.GeometricContextFactory;
import com.lafis.GeometricEfficiencyTool.service.SimulationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
