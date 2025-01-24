package com.lafis.GeometricEfficiencyTool.api.request;

public record SetRectangularApertureRequest(
        String simulationId,
        double width,
        double depth,
        double height
) {
}
