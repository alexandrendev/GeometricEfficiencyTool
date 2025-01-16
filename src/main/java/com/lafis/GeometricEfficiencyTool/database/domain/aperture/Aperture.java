package com.lafis.GeometricEfficiencyTool.database.domain.aperture;

import com.lafis.GeometricEfficiencyTool.database.domain.simulation.Coordinate;
import com.lafis.GeometricEfficiencyTool.database.domain.simulation.Direction;

//@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
//@JsonSubTypes({
//        @JsonSubTypes.Type(value = CircularAperture.class, name = "circular"),
//        @JsonSubTypes.Type(value = RectangularAperture.class, name = "rectangular")
//})
public abstract class Aperture {
    double height;


    public abstract boolean checkIfEmissionEscaped(Direction direction, Coordinate startPoint);

    public Aperture(double height) {
        this.height = height;
    }

    public double getHeight() {
        return height;
    }
}
