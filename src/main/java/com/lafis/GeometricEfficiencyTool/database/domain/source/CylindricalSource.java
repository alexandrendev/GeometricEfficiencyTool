package com.lafis.GeometricEfficiencyTool.database.domain.source;

import com.lafis.GeometricEfficiencyTool.database.domain.simulation.Coordinate;

import java.util.Random;

public class CylindricalSource extends Source{
    private double height;
    private double radius;
    private final Random random = new Random();

    @Override
    public Coordinate randomizeEmitionPoint(Double height) {
        double r = Math.sqrt(random.nextDouble()) * radius;
        double theta = random.nextDouble() * 2 * Math.PI;
        double x = r * Math.cos(theta);
        double y = r * Math.sin(theta);

        double z = (height != null) ? height : random.nextDouble() * this.height;

        return new Coordinate(x, y, z);
    }

    public CylindricalSource(double height, double radius) {
        this.height = height;
        this.radius = radius;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }
}
