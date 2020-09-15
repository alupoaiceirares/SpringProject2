package com.project2.controllers;


import com.project2.entities.User;
import com.project2.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class RegisterController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String registerForm(Model model){

        model.addAttribute("user", new User());

        return "views/registerForm";
    }

    @PostMapping("/register")
    public String registerUser(@Valid User user, BindingResult bindingResult, Model model){
        //in caz ca exista erori in registerForm.html in form apare un msj de eroare
        if(bindingResult.hasErrors()){
            return "views/registerForm";
        }

        if(userService.isUserPresent(user.getEmail())){
            model.addAttribute("exists", true);
            return "views/registerForm";
        }
        else{
            userService.createUser(user);
            return "views/success";
        }




    }
}
