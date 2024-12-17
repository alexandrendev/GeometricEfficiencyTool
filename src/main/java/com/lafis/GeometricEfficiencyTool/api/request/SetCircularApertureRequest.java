package com.lafis.GeometricEfficiencyTool.api.request;

public record SetCircularApertureRequest(
        String simulationId,
        double radius,
        double height
) {
}
