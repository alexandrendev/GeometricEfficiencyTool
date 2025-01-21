package com.lafis.GeometricEfficiencyTool.database.domain.source;

import com.lafis.GeometricEfficiencyTool.database.domain.simulation.Coordinate;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class CylindricalSource extends Source{
    private double height;
    private double radius;


    @Override
    public Coordinate randomizeEmitionPoint(Double bottomHeight) {
        Random random = new Random();
        double distance = random.nextDouble() * radius;
        double phi = random.nextDouble() * 2 * Math.PI;

        double z = bottomHeight + random.nextDouble() * (height - bottomHeight);

        double x = distance * Math.cos(phi);
        double y = distance * Math.sin(phi);

        return new Coordinate(x, y, z);
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
