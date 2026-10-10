package com.app.ecom.controller;

import com.app.ecom.User;
import com.app.ecom.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/users")
public class UserController {

    @Autowired
    private final UserService userService;


    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        return new ResponseEntity<>(userService.buscarAllUser(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id){
        return userService.buscarUser(id).
                map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PostMapping
    public ResponseEntity<String> createUsers(@RequestBody User user){
        userService.addUser(user);
        return ResponseEntity.ok("User Added successfully");
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id,
                                             @RequestBody User updateUser){
        boolean updated = userService.updateUser(id, updateUser);
        if(updated){
            return  ResponseEntity.ok("User updated successfully");
        }
        return ResponseEntity.notFound().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        boolean deletado = userService.deleteUser(id);
        if (deletado){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
