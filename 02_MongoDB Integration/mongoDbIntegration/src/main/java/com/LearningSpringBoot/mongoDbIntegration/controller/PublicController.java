package com.LearningSpringBoot.mongoDbIntegration.controller;

import com.LearningSpringBoot.mongoDbIntegration.entity.User;
import com.LearningSpringBoot.mongoDbIntegration.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {
    @Autowired
    private UserService userService;

    @PostMapping("/create-user")
    public void createUser(@RequestBody User user){
        userService.saveEntry(user);
    }
}
