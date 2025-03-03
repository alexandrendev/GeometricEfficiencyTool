package com.lafis.GeometricEfficiencyTool.database.domain.source;

import com.lafis.GeometricEfficiencyTool.database.domain.simulation.Coordinate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Random;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CuboidSource extends Source {
    private double height;
    private double width;
    private double depth;

    public Coordinate randomizeEmitionPoint(Double bottomHeight) {
        Random random = new Random();

        double x = (random.nextDouble() * 2 - 1) * width / 2;

        double y = (random.nextDouble() * 2 - 1) * depth / 2;

        double z = bottomHeight + random.nextDouble() * height;

        return new Coordinate(x, y, z);
    }
}
