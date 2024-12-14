package com.lafis.GeometricEfficiencyTool.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Random;


@AllArgsConstructor
@Getter
public class Simulation {
    private GeometricContext context;
    private int emissions;
    private int escaped = 0;

    public void incrementEscaped(){
        escaped++;
    }

    public GeometricContext getContext() {
        return context;
    }

    public int getEmissions() {
        return emissions;
    }

    public int getEscaped() {
        return escaped;
    }

    public Simulation(GeometricContext context, int emissions) {
        this.context = context;
        this.emissions = emissions;
    }
}
