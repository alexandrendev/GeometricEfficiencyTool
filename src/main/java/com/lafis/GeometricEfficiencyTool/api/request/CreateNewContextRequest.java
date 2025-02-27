package com.lafis.GeometricEfficiencyTool.api.request;

import com.lafis.GeometricEfficiencyTool.database.domain.aperture.Aperture;
import com.lafis.GeometricEfficiencyTool.database.domain.simulation.ApertureType;
import com.lafis.GeometricEfficiencyTool.database.domain.simulation.SourceType;
import com.lafis.GeometricEfficiencyTool.database.domain.source.Source;

public record CreateNewContextRequest(
        int emissions,
        double sourceHeight,
        ApertureType apertureType,
        Aperture aperture,
        SourceType sourceType,
        Source source
) {
}
