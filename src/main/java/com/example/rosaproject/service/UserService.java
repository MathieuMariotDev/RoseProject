package com.example.rosaproject.service;

import com.example.rosaproject.controller.dto.CreateUserDto;
import com.example.rosaproject.controller.entity.Users;
import com.example.rosaproject.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public class UserService {

    private UserRepository userRepository;

    private StorageService storageService;

    public UserService(UserRepository userRepository, StorageService storageService) {
        this.userRepository = userRepository;
        this.storageService = storageService;
    }

    public void createUser(CreateUserDto createUserDto) {
        MultipartFile picture = createUserDto.getPictureFile();
         storageService.save(picture);
         createUserDto.setPicture("http://localhost:8080/images/" + picture.getOriginalFilename());
         userRepository.save(createUserDto.toUser());
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}

