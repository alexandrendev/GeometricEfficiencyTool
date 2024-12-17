package com.lafis.GeometricEfficiencyTool.service;

import com.lafis.GeometricEfficiencyTool.database.domain.aperture.Aperture;
import com.lafis.GeometricEfficiencyTool.database.domain.simulation.Coordinate;
import com.lafis.GeometricEfficiencyTool.database.domain.simulation.Direction;
import com.lafis.GeometricEfficiencyTool.database.domain.simulation.GeometricContext;
import com.lafis.GeometricEfficiencyTool.database.domain.simulation.Simulation;
import com.lafis.GeometricEfficiencyTool.database.repository.SimulationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class SimulationService {
    private SimulationRepository repository;
    private final Random rd = new Random();

    public void execute(Simulation simulation){
        for (int i = 0; i < simulation.getEmissions(); i++){
            Coordinate emissionPoint = simulation.getContext().getSource().randomizeEmitionPoint();
            Direction direction = emit();

            if(simulation.getContext().getAperture().checkIfEmissionEscaped(direction, emissionPoint)) simulation.incrementEscaped();
        }
    }

    public Simulation save(){
        return repository.save(new Simulation());
    }
    public Simulation save(Simulation simulation){
        return repository.save(simulation);
    }

    public Simulation setAperture(String simulationId, Aperture aperture){
        Simulation simulation = repository.findById(simulationId).orElse(null);
        if (simulation == null) return null;
        simulation.setContext(new GeometricContext());
        simulation.getContext().setAperture(aperture);
        return this.save(simulation);
    }

    public Direction emit(){
        double theta = rd.nextDouble() * Math.PI;
        double phi = rd.nextDouble() * 2 * Math.PI;

        return new Direction(theta, phi);
    }

    @Autowired
    public SimulationService(SimulationRepository repository) {
        this.repository = repository;
    }
}
