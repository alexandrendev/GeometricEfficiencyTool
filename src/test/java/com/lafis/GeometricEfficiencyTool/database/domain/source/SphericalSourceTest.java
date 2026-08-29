package com.lafis.GeometricEfficiencyTool.database.domain.source;

import com.lafis.GeometricEfficiencyTool.database.domain.simulation.Coordinate;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SphericalSourceTest {

    @Test
    void samplesPointsUniformlyInsideTheSphere() {
        double radius = 3.0;
        SphericalSource source = new SphericalSource(radius, 1.0, -2.0, 4.0);
        double sumSquaredZOffset = 0;
        int samples = 100_000;

        for (int i = 0; i < samples; i++) {
            Coordinate point = source.randomizeEmitionPoint(null);
            double xOffset = point.getX() - 1.0;
            double yOffset = point.getY() + 2.0;
            double zOffset = point.getZ() - 4.0;

            assertTrue(xOffset * xOffset + yOffset * yOffset + zOffset * zOffset <= radius * radius);
            sumSquaredZOffset += zOffset * zOffset;
        }

        // For a uniform solid sphere, E[z²] = R² / 5.
        assertEquals(radius * radius / 5, sumSquaredZOffset / samples, 0.03);
    }
}
