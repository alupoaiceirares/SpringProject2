package com.project2;

import com.project2.entities.User;
import com.project2.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Project2Application implements CommandLineRunner {

    @Autowired
    private UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(Project2Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception{

        User newAdmin = new User("admin@yahoo.com", "Admin", "123456");
        userService.createAdmin(newAdmin);
    }

}
