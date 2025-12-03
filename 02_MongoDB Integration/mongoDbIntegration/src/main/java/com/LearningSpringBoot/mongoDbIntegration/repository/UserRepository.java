package com.LearningSpringBoot.mongoDbIntegration.repository;

import com.LearningSpringBoot.mongoDbIntegration.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, ObjectId> {
    User findByUserName(String username);
}
