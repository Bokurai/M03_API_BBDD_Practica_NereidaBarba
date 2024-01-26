package com.example.restservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    public List<UserDTO> readAll() {
        List<UserDTO> userDTOS;
        userDTOS = userService.readAllUsers().stream().map(UserDTO::new).toList();
        return userDTOS;
    }

    public UserDTO getUserById(Integer id) {
        return new UserDTO(userService.getUserById(id));
    }

    public UserDTO addUser(User user) {
       return new UserDTO(userService.addUser(user));
    }

    public void deleteUser(Integer id) {
        userService.deleteUser(id);
    }
}
