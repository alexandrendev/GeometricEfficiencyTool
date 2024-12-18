package com.lafis.GeometricEfficiencyTool.database.domain.source;

import com.lafis.GeometricEfficiencyTool.database.domain.simulation.Coordinate;


public class SphericalSource extends Source{
    private double radius;

    public Coordinate randomizeEmitionPoint() {
        return null;
    }

    public SphericalSource(double radius, double initialHeight, double finalHeight, double increment) {
        super(initialHeight, finalHeight, increment);
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double getInitialHeight() {
        return initialHeight;
    }

    public void setInitialHeight(double initialHeight) {
        this.initialHeight = initialHeight;
    }

    public double getFinalHeight() {
        return finalHeight;
    }

    public void setFinalHeight(double finalHeight) {
        this.finalHeight = finalHeight;
    }

    public double getIncrement() {
        return increment;
    }

    public void setIncrement(int increment) {
        this.increment = increment;
    }
}
