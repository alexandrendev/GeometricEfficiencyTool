package com.lafis.GeometricEfficiencyTool.entity;

import com.lafis.GeometricEfficiencyTool.entity.aperture.Aperture;
import com.lafis.GeometricEfficiencyTool.entity.source.Source;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class GeometricContext {
    private Aperture aperture;
    private Source source;
    private double height;
}
