package com.lafis.GeometricEfficiencyTool.database.repository.user;

import com.lafis.GeometricEfficiencyTool.database.domain.user.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User,String> {
    User findByLogin(String username);
}
