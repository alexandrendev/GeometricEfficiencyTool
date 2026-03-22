package com.lafis.GeometricEfficiencyTool.database.domain.source;

import com.lafis.GeometricEfficiencyTool.database.domain.simulation.Coordinate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Random;

@EqualsAndHashCode(callSuper = true)
@Component
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CylindricalSource extends Source{
    private double height;
    private double radius;
    private double centerX;
    private double centerY;
    private double centerZ;

    public CylindricalSource(double height, double radius) {
        this(height, radius, 0, 0, 0);
    }


    @Override
    public Coordinate randomizeEmitionPoint(Double bottomHeight) {
        Random random = new Random();
        double distance = Math.sqrt(random.nextDouble()) * radius;
        double phi = random.nextDouble() * 2 * Math.PI;

        double z = centerZ + (random.nextDouble() * 2 - 1) * height / 2;

        double x = centerX + distance * Math.cos(phi);
        double y = centerY + distance * Math.sin(phi);

        return new Coordinate(x, y, z);
    }
}
