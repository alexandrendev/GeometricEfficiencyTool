package com.lafis.GeometricEfficiencyTool.database.domain.aperture;

import com.lafis.GeometricEfficiencyTool.database.domain.simulation.Coordinate;
import com.lafis.GeometricEfficiencyTool.database.domain.simulation.Direction;
import lombok.Builder;

@Builder
public class CircularAperture implements Aperture {
    private double radius;
    private double height;


    public boolean checkIfEmissionEscaped(Direction direction, Coordinate startPoint) {
        Coordinate coordinate = direction.convertToCartesianCoordinate(startPoint ,height);
        if(coordinate.getX() >= -this.radius && coordinate.getX() <= this.radius){
            return coordinate.getY() >= -this.radius && coordinate.getY() <= this.radius;
        }
        return false;
    }

    public CircularAperture(double radius, double height) {
        this.radius = radius;
        this.height = height;
    }

    public double getRadius() {
        return radius;
    }

    public double getHeight() {
        return height;
    }
}
