package com.LearningSpringBoot.mongoDbIntegration.service;

import com.LearningSpringBoot.mongoDbIntegration.entity.JournalEntry;
import com.LearningSpringBoot.mongoDbIntegration.entity.User;
import com.LearningSpringBoot.mongoDbIntegration.repository.JournalEntryRepository;
import com.LearningSpringBoot.mongoDbIntegration.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void saveEntry(User user){
       try {
           userRepository.save(user);
       } catch (Exception e) {
           log.error("Exception", e);
       }
    }

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public Optional<User> findById(ObjectId id){
        return userRepository.findById(id);
    }

    public void deleteById(ObjectId id){
        userRepository.deleteById(id);
    }

    public User findByUserName(String userName){ return userRepository.findByUserName(userName); }
}