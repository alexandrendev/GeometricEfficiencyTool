package com.lafis.GeometricEfficiencyTool.service;

import com.lafis.GeometricEfficiencyTool.database.domain.aperture.Aperture;
import com.lafis.GeometricEfficiencyTool.database.domain.simulation.*;
import com.lafis.GeometricEfficiencyTool.database.domain.source.Source;
import com.lafis.GeometricEfficiencyTool.database.repository.SimulationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class SimulationService {
    private SimulationRepository repository;
    private final Random rd = new Random();


    @Async
    public void execute(Simulation simulation) {
        if (simulation == null || simulation.getContext() == null || simulation.getContext().getSource() == null) {
            throw new IllegalArgumentException("Simulation or its context cannot be null");
        }

        simulation.setStatus(SimulationStatus.RUNNING);
        int emissions = simulation.getEmissions();

        for (int i = 0; i < emissions; i++) {
            Coordinate emissionPoint = generateEmissionPoint(simulation);
            Direction direction = emit();

            if (hasEmissionEscaped(simulation, emissionPoint, direction)) {
                simulation.incrementEscaped();
            }
        }
    }

    private Coordinate generateEmissionPoint(Simulation simulation) {
        return simulation.getContext().getSource().randomizeEmitionPoint();
    }

    private boolean hasEmissionEscaped(Simulation simulation, Coordinate point, Direction direction) {
        return simulation.getContext().getAperture().checkIfEmissionEscaped(direction, point);
    }

//    @Async
//    public void execute(Simulation simulation){
//        simulation.setStatus(SimulationStatus.RUNNING);
//        int emissions = simulation.getEmissions();
//        for (int i = 0; i < emissions; i++){
//            Coordinate emissionPoint = simulation.getContext().getSource().randomizeEmitionPoint();
//            Direction direction = emit();
//            if(simulation.getContext().getAperture().checkIfEmissionEscaped(direction, emissionPoint)) simulation.incrementEscaped();
//        }
//    }

    public Simulation save(){
        Simulation simulation = new Simulation();
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

    public Direction emit(){
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
    public SimulationService(SimulationRepository repository) {
        this.repository = repository;
    }
}
