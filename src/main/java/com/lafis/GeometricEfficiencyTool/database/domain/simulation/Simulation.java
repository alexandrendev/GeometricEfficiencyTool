package com.lafis.GeometricEfficiencyTool.database.domain.simulation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.List;

@Document(collection = "simulation")
public class Simulation {
    @Id
    private String id;
    private GeometricContext context;
    private int emissions;
    private double sourceHeight;
    private int escaped;

    @CreatedDate
    private Instant created;
    @LastModifiedDate
    private Instant modified;


    private SimulationStatus status;
    private ApertureType apertureType;
    private SourceType sourceType;

    public void incrementEscaped() {
        escaped++;
    }

    public int getEscaped() {
        return escaped;
    }

    public void setEscaped(int escaped) {
        this.escaped = escaped;
    }

    public double getSourceHeight() {
        return sourceHeight;
    }

    public void setSourceHeight(double sourceHeight) {
        this.sourceHeight = sourceHeight;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public GeometricContext getContext() {
        return context;
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

    public SimulationStatus getStatus() {
        return status;
    }

    public ApertureType getApertureType() {
        return apertureType;
    }

    public SourceType getSourceType() {
        return sourceType;
    }

    public void setStatus(SimulationStatus status) {
        this.status = status;
    }

    public void setApertureType(ApertureType apertureType) {
        this.apertureType = apertureType;
    }

    public void setSourceType(SourceType sourceType) {
        this.sourceType = sourceType;
    }

    public Simulation(GeometricContext context, int emissions) {
        this.context = context;
        this.emissions = emissions;
        this.status = SimulationStatus.CREATED;
    }

    public Simulation(String id, GeometricContext context, int emissions) {
        this.id = id;
        this.context = context;
        this.emissions = emissions;
        this.status = SimulationStatus.CREATED;
    }

    public Simulation() {
        this.status = SimulationStatus.CREATED;
    }
}
