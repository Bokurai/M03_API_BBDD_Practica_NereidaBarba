package com.example.restservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class UserController {

    ObjectMapper objectMapper;

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

    public UserDTO modifyUser(User user, Integer id) {
        User moddedUser = userService.userDAO.findById(id).orElse(null);

        assert moddedUser != null;
        moddedUser.setId(user.getId());
        moddedUser.setFullName(user.getFullName());
        moddedUser.setEmail(user.getEmail());
        moddedUser.setPassword(user.getPassword());
        return new UserDTO(userService.modifyUser(user));
    }

    //usamos el ObjectMapper para modificar los valores
    private User applyPatchUser(User userMod, JsonPatch jsonPatch) throws  JsonProcessingException, JsonPatchException{
        JsonNode patchU = jsonPatch.apply(objectMapper.convertValue(userMod, JsonNode.class));
        return objectMapper.treeToValue(patchU,User.class);
    }

    public UserDTO patchUser(Integer id, JsonPatch jsonPatch) throws JsonProcessingException, JsonPatchException {
        User user = userService.getUserById(id);
        User modifiedUser = applyPatchUser(user, jsonPatch);
        return  new UserDTO(userService.updateUser(modifiedUser));
    }
}
