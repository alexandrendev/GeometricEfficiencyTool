package com.lafis.GeometricEfficiencyTool.database.domain.aperture;

import com.lafis.GeometricEfficiencyTool.database.domain.simulation.Coordinate;
import com.lafis.GeometricEfficiencyTool.database.domain.simulation.Direction;
import lombok.Builder;

@Builder
public class RectangularAperture implements Aperture {

    private double height;
    private double width;
    private double apertureHeight;

    public boolean checkIfEmissionEscaped(Direction direction, Coordinate startPoint) {
        Coordinate coordinate = direction.convertToCartesianCoordinate(startPoint ,height);
        if(coordinate.getX() >= -this.width/2 && coordinate.getX() <= this.width/2){
            return coordinate.getY() >= -this.height / 2 && coordinate.getY() <= this.height / 2;
        }
        return false;
    }

    public RectangularAperture(double height, double width, double apertureHeight) {
        this.height = height;
        this.width = width;
        this.apertureHeight = apertureHeight;
    }

    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
    }

    public double getApertureHeight() {
        return apertureHeight;
    }
}
