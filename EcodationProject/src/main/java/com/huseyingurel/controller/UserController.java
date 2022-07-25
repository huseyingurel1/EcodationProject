package com.huseyingurel.controller;



import com.huseyingurel.dto.UserDto;
import com.huseyingurel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    private UserService userService;

    //http://localhost:8080/api/v1/user
    @PostMapping("/user")
    public UserDto saveUser(@RequestBody UserDto userDto){
         userService.saveUser(userDto);
        return userDto;
    }

    //http://localhost:8080/api/v1/users
    @GetMapping("/users")
    public List<UserDto> getAllUser(){
        List<UserDto> users = userService.getAllUsers();
        return users;
    }

    //http://localhost:8080/api/v1/users/1
    @PutMapping("/users/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable(name = "id") Long id, @RequestBody  UserDto userDto) {
        ResponseEntity<UserDto> response=userService.updateUser(id,userDto);
        return response.ok(userDto);
    }
    //http://localhost:8080/api/v1/users/1
    @DeleteMapping("/users/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteUser(@PathVariable(name = "id") Long id) {
        userService.deleteUser(id);
        Map<String,Boolean> response= new HashMap<>();
        response.put("Kullanıcı Silindi",Boolean.TRUE);
        return ResponseEntity.ok(response);
    }






}
