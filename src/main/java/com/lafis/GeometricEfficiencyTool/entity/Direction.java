package com.lafis.GeometricEfficiencyTool.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Direction {
    private double theta;
    private double phi;


    public Coordinate convertToCartesianCoordinate(Coordinate startPoint, double apertureHeight){
        return Coordinate.builder()
                        .y(
                                startPoint.getY() + Math.abs(apertureHeight - startPoint.getZ()) * Math.tan(theta) * Math.sin(phi)
                        )
                        .x(
                                startPoint.getX() + Math.abs(apertureHeight - startPoint.getZ()) * Math.tan(theta) * Math.cos(phi)
                        )
                .build();

    }
}
