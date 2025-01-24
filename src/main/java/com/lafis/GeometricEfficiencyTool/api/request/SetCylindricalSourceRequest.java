package com.lafis.GeometricEfficiencyTool.api.request;

public record SetCylindricalSourceRequest(
        String simulationId,
        double sourceHeight,
        double sourceRadius
){}
