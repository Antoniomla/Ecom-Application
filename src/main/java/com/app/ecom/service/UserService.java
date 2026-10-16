package com.app.ecom.service;

import com.app.ecom.dto.AddressesDTO;
import com.app.ecom.dto.UserRequest;
import com.app.ecom.dto.UserResponse;
import com.app.ecom.model.Addresses;
import com.app.ecom.model.User;
import com.app.ecom.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;


    public List<UserResponse> fetchAllUsers(){
        return userRepository.findAll().stream()
                .map(this::mapToUserResponse)
                .collect(Collectors.toList());
    }
    public Optional<UserResponse> fetchUser(Long id) {
        return userRepository.findById(id)
                .map(this::mapToUserResponse);
    }

    public void addUser(UserRequest userRequest){
        //user.setId(nextId++);// id automatico
        User user = new User();
        updateUserFromRequest(user, userRequest); // transformar userRequest em user
        userRepository.save(user);
    }


    public boolean updateUser(Long id, UserRequest userUpdateRequest){
        return userRepository.findById(id)
                .map(existingUser -> {
                    updateUserFromRequest(existingUser,userUpdateRequest);
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

    private UserResponse mapToUserResponse(User user){
        //dados saem da entidade User e vão para UserResponse
        UserResponse response = new UserResponse();
        response.setId(String.valueOf(user.getId()));
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setEmail(user.getEmail());
        response.setPhone(user.getPhone());
        response.setRole(user.getRole());

        if(user.getAddresses() != null){
            AddressesDTO addressesDTO = new AddressesDTO();
            addressesDTO.setStreet(user.getAddresses().getStreet());
            addressesDTO.setCity(user.getAddresses().getCity());
            addressesDTO.setState(user.getAddresses().getState());
            addressesDTO.setCountry(user.getAddresses().getCountry());
            addressesDTO.setZipcode(user.getAddresses().getZipcode());
            response.setAdddress(addressesDTO);
        }
        return response;
    }

    private void updateUserFromRequest(User user, UserRequest userRequest) {
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setEmail(userRequest.getEmail());
        user.setPhone(userRequest.getPhone());
        // userRequest em user
        // userRequest.getFirstName() obtem o nome que veio do cliente e implementa em
        // user.setFirstName, isso serve para todos
        if(userRequest.getAdddress() != null){
            Addresses addresses = new Addresses();
            addresses.setStreet(userRequest.getAdddress().getStreet());
            addresses.setCity(userRequest.getAdddress().getCity());
            addresses.setState(userRequest.getAdddress().getState());
            addresses.setCountry(userRequest.getAdddress().getCountry());
            addresses.setZipcode(userRequest.getAdddress().getZipcode());
            // mesma coisa, só que agora é para endereços
            user.setAddresses(addresses);
        }
    }
}
