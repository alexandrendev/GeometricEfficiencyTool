package com.lafis.GeometricEfficiencyTool.entity.aperture;

import com.lafis.GeometricEfficiencyTool.entity.Coordinate;
import com.lafis.GeometricEfficiencyTool.entity.Direction;

public interface Aperture {
    boolean checkIfEmissionEscaped(Direction direction, Coordinate startPoint);
}
