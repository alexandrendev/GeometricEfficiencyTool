package com.lafis.GeometricEfficiencyTool.database.domain.simulation;

public enum SimulationStatus {
    CREATED(1, "Created"),
    RUNNING(2, "Running"),
    FINISHED(3, "Finished"),;

    private int number;
    private String name;

    SimulationStatus(int number, String name) {
        this.number = number;
        this.name = name;
    }
}
