package com.lafis.GeometricEfficiencyTool.database.domain.simulation;

public enum SourceType {
    CUBOID(1, "CUBOID"),
    CYLINDRICAL(2, "CYLINDRICAL"),
    SPHERICAL(3, "SPHERICAL"),
    POINT(4, "POINT");

    final int num;
    final String name;

    SourceType(int num, String name) {
        this.num = num;
        this.name = name;
    }
}
