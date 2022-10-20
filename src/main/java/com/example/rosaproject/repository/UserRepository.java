package com.example.rosaproject.repository;

import com.example.rosaproject.controller.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {

    User findUsersByEmail(String email);

}
