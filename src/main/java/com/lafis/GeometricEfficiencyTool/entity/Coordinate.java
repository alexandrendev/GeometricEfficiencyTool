package com.lafis.GeometricEfficiencyTool.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Coordinate {
    private double x;
    private double y;
    private double z;
}
