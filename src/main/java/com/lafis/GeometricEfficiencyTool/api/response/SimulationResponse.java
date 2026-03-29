package com.lafis.GeometricEfficiencyTool.api.response;

import com.lafis.GeometricEfficiencyTool.database.domain.simulation.ApertureType;
import com.lafis.GeometricEfficiencyTool.database.domain.simulation.GeometricContext;
import com.lafis.GeometricEfficiencyTool.database.domain.simulation.SimulationStatus;
import com.lafis.GeometricEfficiencyTool.database.domain.simulation.SourceType;

import java.time.Instant;

public record SimulationResponse(
        String id,
        GeometricContext context,
        int emissions,
        double sourceHeight,
        int escaped,
        String duration,
        String userId,
        Instant created,
        Instant modified,
        SimulationStatus status,
        ApertureType apertureType,
        SourceType sourceType,
        double solidAngle,
        double solidAngleError
) {
}
