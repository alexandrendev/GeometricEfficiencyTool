package com.lafis.GeometricEfficiencyTool.service;

import com.lafis.GeometricEfficiencyTool.domain.Coordinate;
import com.lafis.GeometricEfficiencyTool.domain.Direction;
import com.lafis.GeometricEfficiencyTool.domain.Simulation;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class SimulationService {
    private final Random rd = new Random();

    public void execute(Simulation simulation){
        for (int i = 0; i < simulation.getEmissions(); i++){
            Coordinate emissionPoint = simulation.getContext().getSource().randomizeEmitionPoint();
            Direction direction = emit();

            if(simulation.getContext().getAperture().checkIfEmissionEscaped(direction, emissionPoint)) simulation.incrementEscaped();
        }
    }

    public Direction emit(){
        double theta = rd.nextDouble() * Math.PI;
        double phi = rd.nextDouble() * 2 * Math.PI;

        return new Direction(theta, phi);
    }
}
