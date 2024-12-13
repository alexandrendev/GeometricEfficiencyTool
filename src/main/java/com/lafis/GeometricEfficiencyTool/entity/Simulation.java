package com.lafis.GeometricEfficiencyTool.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Random;


@AllArgsConstructor
@Builder
@Data
public class Simulation {
    private GeometricContext context;
    private int emissions;
    private static Random rd;
    private int escaped = 0;

    public void execute(){
        for (int i = 0; i < emissions; i++){
           //Simulation logic
            Coordinate emissionPoint = context.getSource().randomizeEmitionPoint();
            Direction direction = emit(emissionPoint);

            if(context.getAperture().checkIfEmissionEscaped(direction, emissionPoint)) escaped++;
        }
    }

    public Direction emit(Coordinate startPoint){
        double theta = rd.nextDouble() * Math.PI;
        double phi = rd.nextDouble() * 2 * Math.PI;

        return Direction.builder()
                .theta(theta)
                .phi(phi)
        .build();
    }
}
