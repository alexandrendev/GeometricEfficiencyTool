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

    @Override
    public Coordinate randomizeEmitionPoint(Double bottomHeight) {
        Random r = new Random();
        double theta = r.nextDouble() * 2 * Math.PI;
        double phi = r.nextDouble() * Math.PI;
        double radialDistance = Math.cbrt(r.nextDouble()) * radius;

        double x = radialDistance * Math.sin(phi) * Math.cos(theta);
        double y = radialDistance * Math.sin(phi) * Math.sin(theta);
        double z = radialDistance * Math.cos(phi);

        z = bottomHeight + z;

        return new Coordinate(x, y, z);
    }
}
