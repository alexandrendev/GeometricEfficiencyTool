package com.lafis.GeometricEfficiencyTool.database.domain.simulation;

public class Height {
    double height;
    int escaped;

    public Height(double height, int escaped) {
        this.height = height;
        this.escaped = escaped;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public int getEscaped() {
        return escaped;
    }

    public void setEscaped(int escaped) {
        this.escaped = escaped;
    }
}
