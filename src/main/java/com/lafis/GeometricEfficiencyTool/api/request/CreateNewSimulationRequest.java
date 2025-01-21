package com.lafis.GeometricEfficiencyTool.api.request;

public record CreateNewSimulationRequest(
        int emissions,
        double sourceHeight
) {
}
