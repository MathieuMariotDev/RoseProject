package com.example.rosaproject.service;

import com.example.rosaproject.controller.dto.CreateUserDto;
import com.example.rosaproject.exception.UserNotFoundException;
import com.example.rosaproject.controller.entity.User;
import com.example.rosaproject.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


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

    public void deleteUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User userToDelete = userRepository.findUsersByEmail(auth.getName());
        userRepository.delete(userToDelete);
    }


    public User findById(long id) {
       User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));;
        return user;
    }

    public User findByEmail() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findUsersByEmail(auth.getName());
        return user;
    }

    public User findUserByEmail(String email){
        return userRepository.findUsersByEmail(email);
    }

    public void editUser(CreateUserDto createUserDto) {
        User editUser = userRepository.findById(createUserDto.getId())
                .orElseThrow(() -> new UserNotFoundException(createUserDto.getId()));;
        if (createUserDto.getPictureFile().isEmpty() || createUserDto.getPictureFile() == null) {
            createUserDto.toUserModify(editUser);
        }else{
            MultipartFile picture = createUserDto.getPictureFile();
            storageService.save(picture);
            createUserDto.setPicture("http://localhost:8080/images/" + picture.getOriginalFilename());
            createUserDto.toUserModify(editUser);
        }
        userRepository.save(editUser);
    }

    public List<User> adminDisplayAllUsers() {
        List<User> usersList = (List<User>) userRepository.findAll();
        return  usersList;
    }

    public void AdminDeleteUser(Long id) {
        User userToDelete = this.findById(id);
        userRepository.delete(userToDelete);
    }
}

