package com.lafis.GeometricEfficiencyTool.service;

import com.lafis.GeometricEfficiencyTool.database.domain.aperture.Aperture;
import com.lafis.GeometricEfficiencyTool.database.domain.simulation.*;
import com.lafis.GeometricEfficiencyTool.database.domain.source.Source;
import com.lafis.GeometricEfficiencyTool.database.repository.SimulationRepository;
import com.lafis.GeometricEfficiencyTool.infra.random.RandomAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class SimulationService {
    private SimulationRepository repository;
    private final RandomAdapter rd;

    @Async
    public CompletableFuture<Boolean> execute (Simulation simulation) {
        if(simulation.getStatus() == SimulationStatus.FINISHED) {
            return CompletableFuture.completedFuture(false);
        }
        Instant start = Instant.now();
        simulation.setStatus(SimulationStatus.RUNNING);
        save(simulation);

        for(int i = 0; i < simulation.getEmissions(); i++){
            Coordinate startPoint = simulation.getContext().getSource().randomizeEmitionPoint(simulation.getSourceHeight());
            Direction direction = emit(startPoint, simulation.getSourceHeight());

            if(simulation.getContext().getAperture().checkIfEmissionEscaped(direction, startPoint)){

                if (simulation.getSourceHeight() < simulation.getContext().getAperture().getHeight()){
                    simulation.incrementEscaped();
                }
            } else {
                if(simulation.getSourceHeight() > simulation.getContext().getAperture().getHeight()){
                    simulation.incrementEscaped();
                }
            }
        }
        Instant end = Instant.now();
        Duration duration = Duration.between(start, end);

        long seconds = duration.getSeconds();
        long minutes = seconds / 60;
        seconds = seconds % 60;
        long milliseconds = duration.toMillis() % 1000;
        simulation.setDuration(String.format("%d min %d sec %d ms", minutes, seconds, milliseconds));

        simulation.setStatus(SimulationStatus.FINISHED);
        save(simulation);
        return CompletableFuture.completedFuture(true);
    }

    private boolean hasEmissionEscaped(Simulation simulation, Coordinate point, Direction direction) {
        return simulation.getContext().getAperture().checkIfEmissionEscaped(direction, point);
    }

    public Simulation save(int emissions, double sourceHeight, String userId){
        Simulation simulation = new Simulation();
        simulation.setEmissions(emissions);
        simulation.setSourceHeight(sourceHeight);
        simulation.setContext(new GeometricContext());
        simulation.setUserId(userId);
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

    public List<Simulation> findAll(String userId){
        return repository.findByUserId(userId).stream().sorted(Comparator.comparing(Simulation::getCreated).reversed()).collect(Collectors.toList());
    }

    public List<Simulation> findRunning(){
        return repository.findByStatus(SimulationStatus.RUNNING.name());
    }

    @Autowired
    public SimulationService(SimulationRepository repository, RandomAdapter randomAdapter) {
        this.repository = repository;
        this.rd = randomAdapter;
    }

}
