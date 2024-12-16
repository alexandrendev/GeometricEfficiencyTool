package com.lafis.GeometricEfficiencyTool.database.domain.simulation;

import lombok.Builder;


@Builder
public class Coordinate {
    private double x;
    private double y;
    private double z;


    public Coordinate(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }
}
