package com.lafis.GeometricEfficiencyTool.infra.random;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class RandomAdapterImpl implements RandomAdapter {
    private Random random;

    @Autowired
    public RandomAdapterImpl(Random random) {
        this.random = random;
    }
    @PostConstruct
    public void init() {
        if (random == null) {
            System.out.println("RandomAdapter NÃO foi injetado!");
        } else {
            System.out.println("RandomAdapter foi injetado com sucesso!");
        }
    }

    @Override
    public double nextDouble() {
        return random.nextDouble();
    }
}
