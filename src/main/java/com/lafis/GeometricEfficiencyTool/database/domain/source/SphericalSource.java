package com.lafis.GeometricEfficiencyTool.database.domain.source;

import com.lafis.GeometricEfficiencyTool.database.domain.simulation.Coordinate;

import java.util.Random;


public class SphericalSource extends Source{
    private double radius;

    @Override
    public Coordinate randomizeEmitionPoint(Double bottomHeight) {
        Random r = new Random();
        double theta = r.nextDouble() * 2 * Math.PI;
        double phi = r.nextDouble() * Math.PI;
        double radialDistance = Math.cbrt(r.nextDouble()) * radius;

        double x = radialDistance * Math.sin(phi) * Math.cos(theta);
        double y = radialDistance * Math.sin(phi) * Math.sin(theta);
        double z = radialDistance * Math.cos(phi);

        z = bottomHeight + z;

        return new Coordinate(x, y, z);
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
