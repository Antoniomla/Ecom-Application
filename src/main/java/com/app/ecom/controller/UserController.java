package com.app.ecom.controller;

import com.app.ecom.User;
import com.app.ecom.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private final UserService userService;


    @GetMapping("/api/users")
    public List<User> getAllUsers(){
        return userService.buscarAllUser();
    }
    @GetMapping("/api/users/{id}")
    public User getUsers(@PathVariable Long id){
        return userService.buscarUser(id);
    }
    @PostMapping("/api/users")
    public String createUsers(@RequestBody User user){
        userService.addUser(user);
        return "User Added successfully";
    }
    @PutMapping("/api/users/{id}")
    public User updateUser(@PathVariable Long id,
                           @RequestBody User userUpdate){
        return userService.updateUser(id,userUpdate);
    }
    @DeleteMapping("api/users/{id}")
    public void deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
    }
}
