package com.lafis.GeometricEfficiencyTool.database.repository.user;


import com.lafis.GeometricEfficiencyTool.database.domain.user.ResetToken;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResetTokenRepository extends MongoRepository<ResetToken,String> {
}
