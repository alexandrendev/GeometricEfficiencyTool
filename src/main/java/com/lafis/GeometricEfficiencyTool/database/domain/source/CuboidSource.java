package com.lafis.GeometricEfficiencyTool.database.domain.source;

import com.lafis.GeometricEfficiencyTool.database.domain.simulation.Coordinate;

public class CuboidSource extends Source {
    private double height;
    private double width;
    private double depht;

    public Coordinate randomizeEmitionPoint(Double height) {
        return null;
    }

    public CuboidSource(double height, double width, double depht) {
        this.height = height;
        this.width = width;
        this.depht = depht;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }
}
