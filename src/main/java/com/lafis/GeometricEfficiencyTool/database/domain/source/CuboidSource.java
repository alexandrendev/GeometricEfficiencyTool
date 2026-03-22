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
    private double centerX;
    private double centerY;
    private double centerZ;

    public CuboidSource(double height, double width, double depth) {
        this(height, width, depth, 0, 0, 0);
    }

    public Coordinate randomizeEmitionPoint(Double bottomHeight) {
        Random random = new Random();

        double x = centerX + (random.nextDouble() * 2 - 1) * width / 2;

        double y = centerY + (random.nextDouble() * 2 - 1) * depth / 2;

        double z = centerZ + (random.nextDouble() * 2 - 1) * height / 2;

        return new Coordinate(x, y, z);
    }
}
