package com.lafis.GeometricEfficiencyTool.api.request;

public record CreateNewSimulationRequest(
        String name,
        int emissions,
        double sourceHeight
) {
}
