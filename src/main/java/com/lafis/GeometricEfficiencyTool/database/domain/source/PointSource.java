package com.lafis.GeometricEfficiencyTool.database.domain.source;

import com.lafis.GeometricEfficiencyTool.database.domain.simulation.Coordinate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PointSource extends Source {
    private double centerX;
    private double centerY;
    private double centerZ;

    @Override
    public Coordinate randomizeEmitionPoint(Double ignoredHeight) {
        return new Coordinate(centerX, centerY, centerZ);
    }
}
