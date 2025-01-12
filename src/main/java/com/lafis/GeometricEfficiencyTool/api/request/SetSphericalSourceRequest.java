package com.lafis.GeometricEfficiencyTool.api.request;

public record SetSphericalSourceRequest(
        String simulationId,
        double sourceRadius
) {
}
