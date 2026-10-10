package com.app.ecom.service;

import com.app.ecom.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private List<User> userList = new ArrayList<>();
    private Long nextId = 1L;

    public List<User> buscarAllUser(){
        return userList;
    }

    public void addUser(User user){
        user.setId(nextId++);// id automatico
        userList.add(user);
    }

    public Optional<User> buscarUser(Long nextId) {
        return userList.stream()
                .filter(user -> user.getId().equals(nextId))
                .findFirst();
    }
    public boolean updateUser(Long id, User userUpdate){
        return userList.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .map(existingUser -> {
                    existingUser.setFristName(userUpdate.getFristName());
                    existingUser.setLastName(userUpdate.getLastName());
                    existingUser.setSenha(userUpdate.getSenha());
                    return true;})
                .orElse(false);
    }

    public boolean deleteUser(Long id) {
        return userList.removeIf(user -> user.getId().equals(id));
    }
}
