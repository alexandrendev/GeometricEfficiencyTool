package com.lafis.GeometricEfficiencyTool.database.domain.simulation;

import com.lafis.GeometricEfficiencyTool.database.domain.aperture.Aperture;
import com.lafis.GeometricEfficiencyTool.database.domain.source.Source;
import lombok.Data;

@Data
public class GeometricContext {
    private Aperture aperture;
    private Source source;

    public GeometricContext(Aperture aperture, Source source) {
        this.aperture = aperture;
        this.source = source;
    }

    public GeometricContext() {
    }

}
