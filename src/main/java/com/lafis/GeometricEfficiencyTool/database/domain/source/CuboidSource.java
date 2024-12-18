package com.lafis.GeometricEfficiencyTool.database.domain.source;

import com.lafis.GeometricEfficiencyTool.database.domain.simulation.Coordinate;

public class CuboidSource extends Source {
    private double height;
    private double width;


    @Override
    public Coordinate randomizeEmitionPoint() {
        return null;
    }

    public CuboidSource(double height, double width, double initialHeight, double finalHeight, double increment) {
        super(initialHeight,finalHeight,increment);
        this.height = height;
        this.width = width;
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
