package com.lafis.GeometricEfficiencyTool.database.domain.simulation;

public enum ApertureType {
    CIRCULAR(1, "Circular"),
    RECTANGULAR(2, "Rectangular"),;

    final int num;
    final String name;

    ApertureType(int num, String name) {
        this.num = num;
        this.name = name;
    }
}
