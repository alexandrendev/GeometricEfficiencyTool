package com.lafis.GeometricEfficiencyTool.service;

import com.lafis.GeometricEfficiencyTool.domain.GeometricContext;
import com.lafis.GeometricEfficiencyTool.domain.aperture.Aperture;
import com.lafis.GeometricEfficiencyTool.domain.source.Source;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class GeometricContextFactory {
    private final Map<String, Aperture> apertures;
    private final Map<String, Source> sources;

    @Autowired
    public GeometricContextFactory(Map<String, Aperture> apertures, Map<String, Source> sources) {
        this.apertures = apertures;
        this.sources = sources;
    }

    public GeometricContext createContext(Aperture aperture, Source source, double height) {

        if (aperture == null || source == null) {
            throw new IllegalArgumentException("Invalid aperture or source type");
        }

        return new GeometricContext(aperture, source, height);
    }
}
