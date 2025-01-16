package com.lafis.GeometricEfficiencyTool.service;

import com.lafis.GeometricEfficiencyTool.database.domain.aperture.Aperture;
import com.lafis.GeometricEfficiencyTool.database.domain.simulation.*;
import com.lafis.GeometricEfficiencyTool.database.domain.source.Source;
import com.lafis.GeometricEfficiencyTool.database.repository.SimulationRepository;
import com.lafis.GeometricEfficiencyTool.infra.random.RandomAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class SimulationService {
    private SimulationRepository repository;
    private final RandomAdapter rd;

    public void execute (Simulation simulation) {

        for (Height height : simulation.getHeights()) {
            for(int i = 0; i < simulation.getEmissions(); i++){
                Coordinate startPoint = simulation.getContext().getSource().randomizeEmitionPoint(height.getHeight());
                Direction direction = emit();

                if(simulation.getContext().getAperture().checkIfEmissionEscaped(direction, startPoint)){
                    height.incrementEscaped();
                }
            }
        }

        save(simulation);
    }

    private boolean hasEmissionEscaped(Simulation simulation, Coordinate point, Direction direction) {
        return simulation.getContext().getAperture().checkIfEmissionEscaped(direction, point);
    }

    public Simulation save(int emissions, double increment, double finalHeight){
        Simulation simulation = new Simulation();

        List<Height> heights = new ArrayList<>();
        for (double height = 0; height <= finalHeight; height += increment) {
            heights.add(new Height(height, 0));
        }

        simulation.setEmissions(emissions);
        simulation.setHeights(heights);

        simulation.setContext(new GeometricContext());
        return repository.save(simulation);
    }
    public Simulation save(Simulation simulation){
        return repository.save(simulation);
    }

    public Simulation setAperture(String simulationId, Aperture aperture, ApertureType apertureType){
        Simulation simulation = repository.findById(simulationId).orElse(null);
        if (simulation == null) return null;
        simulation.setApertureType(apertureType);
        simulation.getContext().setAperture(aperture);
        return this.save(simulation);
    }
    
    public Simulation setSource(String simulationId, Source source, SourceType sourceType){
        Simulation simulation = repository.findById(simulationId).orElse(null);
        if (simulation == null) return null;
        simulation.setSourceType(sourceType);
        simulation.getContext().setSource(source);
        return this.save(simulation);
    }

    public Direction emit(Coordinate startPoint, double height){
        double theta = rd.nextDouble() * Math.PI;
        double phi = rd.nextDouble() * 2 * Math.PI;

        return new Direction(theta, phi);
    }

    public Simulation findById(String simulationId){
        return repository.findById(simulationId).orElse(null);
    }

    public List<Simulation> findAll(){
        return repository.findAll();
    }
    @Autowired
    public SimulationService(SimulationRepository repository, RandomAdapter randomAdapter) {
        this.repository = repository;
        this.rd = randomAdapter;
    }
}
