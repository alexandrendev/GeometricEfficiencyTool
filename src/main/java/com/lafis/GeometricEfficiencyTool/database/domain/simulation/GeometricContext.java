package com.lafis.GeometricEfficiencyTool.database.domain.simulation;

import com.lafis.GeometricEfficiencyTool.database.domain.aperture.Aperture;
import com.lafis.GeometricEfficiencyTool.database.domain.source.Source;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GeometricContext {
    private Aperture aperture;
    private Source source;
    private double height;

    public GeometricContext(Aperture aperture, Source source, double height) {
        this.aperture = aperture;
        this.source = source;
    }

    public Aperture getAperture() {
        return aperture;
    }

    public Source getSource() {
        return source;
    }

    public double getHeight() {
        return height;
    }
}
