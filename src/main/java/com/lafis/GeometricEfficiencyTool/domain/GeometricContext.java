package com.lafis.GeometricEfficiencyTool.domain;

import com.lafis.GeometricEfficiencyTool.domain.aperture.Aperture;
import com.lafis.GeometricEfficiencyTool.domain.source.Source;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

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
