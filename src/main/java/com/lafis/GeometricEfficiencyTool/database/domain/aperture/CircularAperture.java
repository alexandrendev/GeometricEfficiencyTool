package com.lafis.GeometricEfficiencyTool.database.domain.aperture;

import com.lafis.GeometricEfficiencyTool.database.domain.simulation.Coordinate;
import com.lafis.GeometricEfficiencyTool.database.domain.simulation.Direction;

public class CircularAperture extends Aperture {
    private double radius;


    public boolean checkIfEmissionEscaped(Direction direction, Coordinate startPoint) {
        Coordinate coordinate = direction.convertToCartesianCoordinate(startPoint, height);
        double distanceSquared = Math.pow(coordinate.getX(), 2) + Math.pow(coordinate.getY(), 2);
        return distanceSquared <= Math.pow(this.radius, 2);
    }



    public CircularAperture(double height, double radius) {
        super(height);
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

}
