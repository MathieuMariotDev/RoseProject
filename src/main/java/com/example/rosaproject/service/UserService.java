package com.example.rosaproject.service;

import com.example.rosaproject.controller.entity.CreateUserDto;
import com.example.rosaproject.controller.entity.Users;
import com.example.rosaproject.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createUser(CreateUserDto createUserDto) {
        Users newUser = new Users();
        newUser.setPassword(createUserDto.getPassword());
      // newUser.setPicture(createUserDto.getPictureFile());
        newUser.setEmail(createUserDto.getEmail());
        newUser.setRole((short) 1);

//        MultipartFile picture = createUserDto.getPictureFile();
//            storageService.store(picture);
//            newUser.setPicture("http://localhost:8080/images/" + picture.getOriginalFilename());
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}

