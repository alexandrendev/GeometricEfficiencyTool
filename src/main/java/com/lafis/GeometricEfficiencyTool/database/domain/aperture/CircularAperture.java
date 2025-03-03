package com.lafis.GeometricEfficiencyTool.database.domain.aperture;

import com.lafis.GeometricEfficiencyTool.database.domain.simulation.Coordinate;
import com.lafis.GeometricEfficiencyTool.database.domain.simulation.Direction;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CircularAperture extends Aperture {
    private double height;
    private double radius;


    public boolean checkIfEmissionEscaped(Direction direction, Coordinate startPoint) {
        Coordinate coordinate = direction.convertToCartesianCoordinate(startPoint, height);
        double distanceSquared = Math.pow(coordinate.getX(), 2) + Math.pow(coordinate.getY(), 2);
        return distanceSquared <= Math.pow(this.radius, 2);
    }
}
