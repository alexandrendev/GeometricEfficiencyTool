package com.lafis.GeometricEfficiencyTool.domain.aperture;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.lafis.GeometricEfficiencyTool.domain.Coordinate;
import com.lafis.GeometricEfficiencyTool.domain.Direction;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = CircularAperture.class, name = "circular"),
        @JsonSubTypes.Type(value = RectangularAperture.class, name = "rectangular")
})
public interface Aperture {
    boolean checkIfEmissionEscaped(Direction direction, Coordinate startPoint);
}
