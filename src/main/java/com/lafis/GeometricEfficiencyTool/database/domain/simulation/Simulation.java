package com.lafis.GeometricEfficiencyTool.database.domain.simulation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.List;

@Document(collection = "simulation")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
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

    @Builder.Default
    private SimulationStatus status = SimulationStatus.CREATED;
    private ApertureType apertureType;
    private SourceType sourceType;

    public void incrementEscaped() {
        escaped++;
    }

    public Simulation(GeometricContext context, int emissions, double sourceHeight) {
        this.context = context;
        this.emissions = emissions;
        this.status = SimulationStatus.CREATED;
        this.sourceHeight = sourceHeight;
    }
}
