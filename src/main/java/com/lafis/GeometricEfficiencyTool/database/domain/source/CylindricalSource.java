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


    @Override
    public Coordinate randomizeEmitionPoint(Double bottomHeight) {
        Random random = new Random();
        double distance = random.nextDouble() * radius;
        double phi = random.nextDouble() * 2 * Math.PI;

        double z = bottomHeight + random.nextDouble() * (height - bottomHeight);

        double x = distance * Math.cos(phi);
        double y = distance * Math.sin(phi);

        return new Coordinate(x, y, z);
    }
}
