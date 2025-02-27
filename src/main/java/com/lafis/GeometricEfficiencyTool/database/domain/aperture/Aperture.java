package com.lafis.GeometricEfficiencyTool.database.domain.aperture;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.lafis.GeometricEfficiencyTool.database.domain.simulation.Coordinate;
import com.lafis.GeometricEfficiencyTool.database.domain.simulation.Direction;
import org.springframework.data.mongodb.core.mapping.Field;


@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = RectangularAperture.class, name = "rectangular"),
        @JsonSubTypes.Type(value = CircularAperture.class, name = "circular")
})
public abstract class Aperture {
    double height;


    public abstract boolean checkIfEmissionEscaped(Direction direction, Coordinate startPoint);

    public Aperture(double height) {
        this.height = height;
    }

    public Aperture() {
    }

    public double getDepth() {
        return height;
    }
}
