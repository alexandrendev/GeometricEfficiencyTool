package com.lafis.GeometricEfficiencyTool.infra.random;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class RandomAdapterImpl implements RandomAdapter {
    private final Random random;

    public RandomAdapterImpl() {
        this.random = new Random();
    }

    @Override
    public double nextDouble() {
        return random.nextDouble();
    }
}
