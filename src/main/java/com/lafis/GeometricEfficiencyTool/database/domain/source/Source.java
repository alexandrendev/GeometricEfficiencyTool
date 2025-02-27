package com.lafis.GeometricEfficiencyTool.database.domain.source;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.lafis.GeometricEfficiencyTool.database.domain.simulation.Coordinate;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = CuboidSource.class, name = "cuboid"),
        @JsonSubTypes.Type(value = CylindricalSource.class, name = "cylindrical"),
        @JsonSubTypes.Type(value = SphericalSource.class, name = "spherical")
})

public abstract class Source {
    public abstract Coordinate randomizeEmitionPoint(Double height);
//    public abstract void setHeight(double height);

    public Source() {
    }


}
