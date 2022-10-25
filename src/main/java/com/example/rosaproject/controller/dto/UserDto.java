package com.example.rosaproject.controller.dto;

import com.example.rosaproject.controller.entity.User;

public class UserDto {

    private long id;

    public UserDto() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public static UserDto from(User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        return userDto;
    }


}
