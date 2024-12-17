package com.lafis.GeometricEfficiencyTool.database.repository;

import com.lafis.GeometricEfficiencyTool.database.domain.simulation.Simulation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SimulationRepository extends MongoRepository<Simulation,String> {

    Simulation save(Simulation simulation);

    Optional<Simulation> findById(String s);
}
