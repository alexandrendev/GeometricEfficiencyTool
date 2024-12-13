package com.lafis.GeometricEfficiencyTool.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Random;


@AllArgsConstructor
@Builder
@Data
public class Simulation {
    private GeometricContext context;
    private int emissions;
    private Random rd;
    private int escaped = 0;

    public void execute(){
        for (int i = 0; i < emissions; i++){
            Coordinate emissionPoint = context.getSource().randomizeEmitionPoint();
            Direction direction = emit();

            if(context.getAperture().checkIfEmissionEscaped(direction, emissionPoint)) escaped++;
        }
    }

    public Direction emit(){
        double theta = rd.nextDouble() * Math.PI;
        double phi = rd.nextDouble() * 2 * Math.PI;

        return Direction.builder()
                .theta(theta)
                .phi(phi)
        .build();
    }

    @Autowired
    public Simulation(Random rd) {
        this.rd = rd;
    }
}
