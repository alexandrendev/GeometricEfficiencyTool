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
public class RectangularAperture extends Aperture {
    private double height;
    private double depth;
    private double width;

    public boolean checkIfEmissionEscaped(Direction direction, Coordinate startPoint) {
        Coordinate coordinate = direction.convertToCartesianCoordinate(startPoint , depth);
        if(coordinate.getX() >= -this.width/2 && coordinate.getX() <= this.width/2){
            return coordinate.getY() >= -this.depth / 2 && coordinate.getY() <= this.depth / 2;
        }
        return false;
    }

}
