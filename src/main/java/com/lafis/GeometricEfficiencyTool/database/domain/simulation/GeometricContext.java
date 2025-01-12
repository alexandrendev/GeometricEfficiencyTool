package com.lafis.GeometricEfficiencyTool.database.domain.simulation;

import com.lafis.GeometricEfficiencyTool.database.domain.aperture.Aperture;
import com.lafis.GeometricEfficiencyTool.database.domain.source.Source;

public class GeometricContext {
    private Aperture aperture;
    private Source source;

    public GeometricContext(Aperture aperture, Source source, double height) {
        this.aperture = aperture;
        this.source = source;
    }

    public GeometricContext() {
    }

    public Aperture getAperture() {
        return aperture;
    }

    public Source getSource() {
        return source;
    }


    public void setAperture(Aperture aperture) {
        this.aperture = aperture;
    }

    public void setSource(Source source) {
        this.source = source;
    }
}
