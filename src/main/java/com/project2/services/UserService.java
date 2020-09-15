package com.project2.services;

import com.project2.entities.Role;
import com.project2.entities.User;
import com.project2.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void createUser(User user){
        //criptam parola si o setam user-ului
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));

        Role userRole = new Role("USER");
        List<Role> roles = new ArrayList<>();
        roles.add(userRole);
        user.setRoles(roles);
        userRepository.save(user);

    }

    public void createAdmin(User user){
        //criptam parola si o setam user-ului
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));

        Role userRole = new Role("ADMIN");
        List<Role> roles = new ArrayList<>();
        roles.add(userRole);
        user.setRoles(roles);
        userRepository.save(user);

    }


    /*public Optional<User> findOne(String email) {
        return userRepository.findById(email);
    }*/

    public User findById(String email){
        return userRepository.findById(email).orElse(null);
    }

    public boolean isUserPresent(String email) {
        User u = userRepository.findById(email).orElse(null);
        if(u!= null)
            return  true;
        return false;
    }

    public  List<User> findAll() {
        return (List<User>) userRepository.findAll();
    }

    public List<User> findByName(String name) {
        return userRepository.findByNameLike(name);
    }
}
