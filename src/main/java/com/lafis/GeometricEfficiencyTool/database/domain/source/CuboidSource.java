package com.lafis.GeometricEfficiencyTool.database.domain.source;

import com.lafis.GeometricEfficiencyTool.database.domain.simulation.Coordinate;

import java.util.Random;

public class CuboidSource extends Source {
    private double height;
    private double width;
    private double depth;

    public Coordinate randomizeEmitionPoint(Double bottomHeight) {
        Random random = new Random();

        double x = (random.nextDouble() * 2 - 1) * width / 2;

        double y = (random.nextDouble() * 2 - 1) * depth / 2;

        double z = bottomHeight + random.nextDouble() * height;

        return new Coordinate(x, y, z);
    }

    public CuboidSource(double height, double width, double depth) {
        this.height = height;
        this.width = width;
        this.depth = depth;
    }

    public CuboidSource() {
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

    public double getDepth() {
        return depth;
    }

    public void setDepth(double depth) {
        this.depth = depth;
    }
}
