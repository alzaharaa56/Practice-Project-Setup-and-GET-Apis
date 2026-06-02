package com.example.trainee_app;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String welcomeMessage(){
        return "Welcome to Code Creators Spring Boot Training";
    }

    @GetMapping("/help")
    public String helpMessage(){
        return "If you are stuck, please post your error screenshot on Slack!";
    }

    @GetMapping("/profile")
    public Trainee getProfile() {
        return new Trainee();
    }
}
