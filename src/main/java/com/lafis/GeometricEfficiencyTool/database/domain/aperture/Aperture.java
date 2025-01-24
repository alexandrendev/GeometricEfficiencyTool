package com.lafis.GeometricEfficiencyTool.database.domain.aperture;

import com.lafis.GeometricEfficiencyTool.database.domain.simulation.Coordinate;
import com.lafis.GeometricEfficiencyTool.database.domain.simulation.Direction;
import org.springframework.data.mongodb.core.mapping.Field;

public abstract class Aperture {
    double height;


    public abstract boolean checkIfEmissionEscaped(Direction direction, Coordinate startPoint);

    public Aperture(double height) {
        this.height = height;
    }

    public double getDepth() {
        return height;
    }
}
