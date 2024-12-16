package com.lafis.GeometricEfficiencyTool.database.domain.simulation;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "simulation")
public class Simulation {
    @Id
    private String id;
    private GeometricContext context;
    private int emissions;
    private int escaped = 0;

    public void incrementEscaped(){
        escaped++;
    }

    public GeometricContext getContext() {
        return context;
    }

    public Simulation(GeometricContext context, int emissions) {
        this.context = context;
        this.emissions = emissions;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setContext(GeometricContext context) {
        this.context = context;
    }

    public int getEmissions() {
        return emissions;
    }

    public void setEmissions(int emissions) {
        this.emissions = emissions;
    }

    public int getEscaped() {
        return escaped;
    }

    public void setEscaped(int escaped) {
        this.escaped = escaped;
    }
}
