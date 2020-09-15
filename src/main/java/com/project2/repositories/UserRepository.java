package com.project2.repositories;

import com.project2.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, String> {

    List<User> findByNameLike(String name);
    //User findOne(String email);
}
