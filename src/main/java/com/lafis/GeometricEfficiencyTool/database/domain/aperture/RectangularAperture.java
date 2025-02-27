package com.lafis.GeometricEfficiencyTool.database.domain.aperture;

import com.lafis.GeometricEfficiencyTool.database.domain.simulation.Coordinate;
import com.lafis.GeometricEfficiencyTool.database.domain.simulation.Direction;

public class RectangularAperture extends Aperture {

    private double depth;
    private double width;

    public boolean checkIfEmissionEscaped(Direction direction, Coordinate startPoint) {
        Coordinate coordinate = direction.convertToCartesianCoordinate(startPoint , depth);
        if(coordinate.getX() >= -this.width/2 && coordinate.getX() <= this.width/2){
            return coordinate.getY() >= -this.depth / 2 && coordinate.getY() <= this.depth / 2;
        }
        return false;
    }

    public RectangularAperture(double depth, double width, double height) {
        super(height);
        this.depth = depth;
        this.width = width;
    }

    public RectangularAperture(double height) {
        super(height);
    }

    public double getDepth() {
        return depth;
    }

    public double getWidth() {
        return width;
    }

}
