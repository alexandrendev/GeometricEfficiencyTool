package com.lafis.GeometricEfficiencyTool.database.domain.simulation;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Coordinate {
    private double x;
    private double y;
    private double z;

    public Coordinate(double x, double y) {
        this.x = x;
        this.y = y;
    }
}
