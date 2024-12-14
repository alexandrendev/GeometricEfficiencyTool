package com.lafis.GeometricEfficiencyTool.domain.aperture;

import com.lafis.GeometricEfficiencyTool.domain.Coordinate;
import com.lafis.GeometricEfficiencyTool.domain.Direction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@Builder
public class RectangularAperture implements Aperture {

    private double height;
    private double width;

    public boolean checkIfEmissionEscaped(Direction direction, Coordinate startPoint) {
        Coordinate coordinate = direction.convertToCartesianCoordinate(startPoint ,height);
        if(coordinate.getX() >= -this.width/2 && coordinate.getX() <= this.width/2){
            return coordinate.getY() >= -this.height / 2 && coordinate.getY() <= this.height / 2;
        }
        return false;
    }


}
