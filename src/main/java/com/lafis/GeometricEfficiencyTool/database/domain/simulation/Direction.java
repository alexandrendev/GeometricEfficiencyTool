package com.lafis.GeometricEfficiencyTool.database.domain.simulation;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Direction {
    private double theta;
    private double phi;

    public Coordinate convertToCartesianCoordinate(Coordinate startPoint, double apertureHeight){
        double y = startPoint.getY() + Math.abs(apertureHeight - startPoint.getZ()) * Math.tan(theta) * Math.sin(phi);
        double x = startPoint.getX() + Math.abs(apertureHeight - startPoint.getZ()) * Math.tan(theta) * Math.cos(phi);
        
        return new Coordinate(x, y);
    }
}
