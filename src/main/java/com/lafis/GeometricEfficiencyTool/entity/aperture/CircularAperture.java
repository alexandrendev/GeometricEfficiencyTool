package com.lafis.GeometricEfficiencyTool.entity.aperture;

import com.lafis.GeometricEfficiencyTool.entity.Coordinate;
import com.lafis.GeometricEfficiencyTool.entity.Direction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class CircularAperture implements Aperture {
    private double radius;
    private double height;


    public boolean checkIfEmissionEscaped(Direction direction, Coordinate startPoint) {
        Coordinate coordinate = direction.convertToCartesianCoordinate(startPoint ,height);
        if(coordinate.getX() >= -this.radius && coordinate.getX() <= this.radius){
            return coordinate.getY() >= -this.radius && coordinate.getY() <= this.radius;
        }
        return false;
    }
}
