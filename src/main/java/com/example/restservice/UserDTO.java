package com.example.restservice;

import lombok.Data;

@Data
public class UserDTO {
    private Integer id;
    private String email;
    private String fullName;

    public UserDTO(){

    }

    public UserDTO(Integer id, String email, String fullName) {
        this.id = id;
        this.email = email;
        this.fullName = fullName;
    }

    public UserDTO(User user){
        id = user.getId();
        email = user.getEmail();
        fullName = user.getFullName();
    }
}
