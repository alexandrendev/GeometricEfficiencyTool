package com.lafis.GeometricEfficiencyTool.api.request;

public record SetCuboidSourceRequest(
        String simulationId,
        double sourceHeight,
        double sourceWidth,
        double sourceDepth,
        double initialHeight,
        double finalHeight,
        double increment
) {
}
