package com.lafis.GeometricEfficiencyTool.database.domain.source;

import com.lafis.GeometricEfficiencyTool.database.domain.simulation.Coordinate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Random;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SphericalSource extends Source{
    private double radius;
    private double centerX;
    private double centerY;
    private double centerZ;

    public SphericalSource(double radius) {
        this(radius, 0, 0, 0);
    }

    @Override
    public Coordinate randomizeEmitionPoint(Double bottomHeight) {
        Random r = new Random();
        double theta = r.nextDouble() * 2 * Math.PI;
        double cosPhi = 2 * r.nextDouble() - 1;
        double sinPhi = Math.sqrt(1 - cosPhi * cosPhi);
        double radialDistance = Math.cbrt(r.nextDouble()) * radius;

        double x = centerX + radialDistance * sinPhi * Math.cos(theta);
        double y = centerY + radialDistance * sinPhi * Math.sin(theta);
        double z = centerZ + radialDistance * cosPhi;

        return new Coordinate(x, y, z);
    }
}
