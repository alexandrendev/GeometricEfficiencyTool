package com.lafis.GeometricEfficiencyTool.api.request;

public record SetPointSourceRequest(
        String simulationId,
        double centerX,
        double centerY,
        double centerZ
) {
}
