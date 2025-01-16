package com.lafis.GeometricEfficiencyTool.database.domain.source;

import com.lafis.GeometricEfficiencyTool.database.domain.simulation.Coordinate;


public class SphericalSource extends Source{
    private double radius;

    @Override
    public Coordinate randomizeEmitionPoint(Double height) {
        return null;
    }

    public SphericalSource(double radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }
}
