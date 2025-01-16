package com.lafis.GeometricEfficiencyTool.database.domain.source;

import com.lafis.GeometricEfficiencyTool.database.domain.simulation.Coordinate;

import com.lafis.GeometricEfficiencyTool.infra.random.RandomAdapter;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CylindricalSource extends Source{
    private double height;
    private double radius;
    private RandomAdapter random;

    @Override
    public Coordinate randomizeEmitionPoint(Double height) {
        double distance = random.nextDouble() * radius;
        double phi = random.nextDouble() * 2 * Math.PI;

        return new Coordinate(x, y, z);
    }

    @Autowired
    public CylindricalSource(RandomAdapter random) {
        this.random = random;
    }

    @PostConstruct
    public void init() {
        if (random == null) {
            System.out.println("RandomAdapter NÃO foi injetado!");
        } else {
            System.out.println("RandomAdapter foi injetado com sucesso!");
        }
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
