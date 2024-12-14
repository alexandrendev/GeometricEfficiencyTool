package com.lafis.GeometricEfficiencyTool.api.request;

import com.lafis.GeometricEfficiencyTool.domain.aperture.Aperture;
import com.lafis.GeometricEfficiencyTool.domain.source.Source;

public class NewContextRequest {
    private String apertureType;
    private String sourceType;
    private double height;
    private int emissions;
    private Source source;
    private Aperture aperture;

    public String getApertureType() {
        return apertureType;
    }

    public String getSourceType() {
        return sourceType;
    }

    public double getHeight() {
        return height;
    }

    public int getEmissions() {
        return emissions;
    }

    public Source getSource() {
        return source;
    }

    public Aperture getAperture() {
        return aperture;
    }
}
