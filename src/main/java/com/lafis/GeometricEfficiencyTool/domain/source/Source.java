package com.lafis.GeometricEfficiencyTool.domain.source;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.lafis.GeometricEfficiencyTool.domain.Coordinate;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = CylindricalSource.class, name = "cylindrical"),
        @JsonSubTypes.Type(value = RectangularSource.class, name = "rectangular"),
        @JsonSubTypes.Type(value = EsphericalSource.class, name = "espherical")
})
public interface Source {

    Coordinate randomizeEmitionPoint();
}
