package com.app.ecom.service;

import com.app.ecom.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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

    public User buscarUser(Long nextId) {
        for(User user : userList){
            if(user.getId().equals(nextId)){
                return user;
            }
        }
        return null;
    }
    public User updateUser(Long id, User userUpdate){
        for(User user : userList){
            if(user.getId().equals(id)){
                user.setFristName(userUpdate.getFristName());
                user.setLastName(userUpdate.getLastName());
                user.setSenha(userUpdate.getSenha());

                return user;
            }
        }
        return null;
    }

    public void deleteUser(Long id) {
        userList.removeIf(user -> user.getId().equals(id));
    }
}
