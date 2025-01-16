package com.lafis.GeometricEfficiencyTool.database.domain.aperture;

import com.lafis.GeometricEfficiencyTool.database.domain.simulation.Coordinate;
import com.lafis.GeometricEfficiencyTool.database.domain.simulation.Direction;

public class CircularAperture extends Aperture {
    private double radius;


    public boolean checkIfEmissionEscaped(Direction direction, Coordinate startPoint) {
        Coordinate coordinate = direction.convertToCartesianCoordinate(startPoint ,height);
        if(coordinate.getX() >= -this.radius && coordinate.getX() <= this.radius){
            return coordinate.getY() >= -this.radius && coordinate.getY() <= this.radius;
        }
        return false;
    }


    public CircularAperture(double height, double radius) {
        super(height);
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

}
