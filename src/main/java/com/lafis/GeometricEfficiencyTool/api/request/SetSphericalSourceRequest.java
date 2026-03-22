package com.lafis.GeometricEfficiencyTool.api.request;

public record SetSphericalSourceRequest(
        String simulationId,
        double sourceRadius,
        double centerX,
        double centerY,
        double centerZ
) {
}
