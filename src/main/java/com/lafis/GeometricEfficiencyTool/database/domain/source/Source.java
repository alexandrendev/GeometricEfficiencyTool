package com.lafis.GeometricEfficiencyTool.database.domain.source;

import com.lafis.GeometricEfficiencyTool.database.domain.simulation.Coordinate;


public abstract class Source {
    public abstract Coordinate randomizeEmitionPoint(Double height);
//    public abstract void setHeight(double height);

    public Source() {
    }


}
