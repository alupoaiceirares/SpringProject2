package com.project2;

import com.project2.entities.Task;
import com.project2.entities.User;
import com.project2.services.TaskService;
import com.project2.services.UserService;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class Project2ApplicationTests {

    @Autowired
    private UserService userService;

    @Autowired
    private TaskService taskService;

    @BeforeEach
    public void initDB(){
        User user1 = new User("test@gmail.com", "testRares", "12345");
        userService.createUser(user1);
        User user2 = new User("testadmin@gmail.com", "adminrares", "45678");
        userService.createAdmin(user2);

        Task task1 = new Task("12/12/2020", "00:11", "01:25", "the need to eat");
        Task task2 = new Task("05/05/2020", "05:05", "10:25", "the need to sleep");
        User userclasic = userService.findById("test@gmail.com");
        User usertare = userService.findById("testadmin@gmail.com");
        taskService.addTask(task1, userclasic);
        taskService.addTask(task2, usertare);
    }

    @Test
    public void testUser(){

        User user = userService.findById("test@gmail.com");
        assertNotNull(user);
        User admin = userService.findById("testadmin@gmail.com");
        assertEquals(admin.getEmail(), "testadmin@gmail.com");
    }

    @Test
    public void testTask(){
        User usr = userService.findById("test@gmail.com");
        List<Task> task = taskService.findUserTask(usr);
        assertNotNull(task);
    }

}
