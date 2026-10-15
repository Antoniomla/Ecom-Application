package com.app.ecom.service;

import com.app.ecom.model.User;
import com.app.ecom.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    //private List<User> userList = new ArrayList<>();
    //private Long nextId = 1L;

    public List<User> fetchAllUsers(){
        return userRepository.findAll();
    }
    public Optional<User> fetchUser(Long id) {
        return userRepository.findById(id);
    }

    public void addUser(User user){
        //user.setId(nextId++);// id automatico
        userRepository.save(user);
    }

    public boolean updateUser(Long id, User userUpdate){
        return userRepository.findById(id)
                .map(existingUser -> {
                    existingUser.setFirstName(userUpdate.getFirstName());
                    existingUser.setLastName(userUpdate.getLastName());
                    userRepository.save(existingUser);
                    return true;})
                .orElse(false);
    }

    public boolean deleteUser(Long id) {
        if(userRepository.existsById(id)){
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
