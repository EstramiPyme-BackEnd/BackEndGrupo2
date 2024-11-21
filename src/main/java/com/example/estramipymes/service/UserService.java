package com.example.estramipymes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.estramipymes.model.User;
import com.example.estramipymes.repository.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    // Read (GET) all the users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Read (GET) a user by ID
    public User getUser(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    // Create (POST) a new user
    public User createUser(User user) {
        User existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser != null)
            return null;

        return userRepository.save(user);
    }
    
    // Remove (DELETE) an existing user
    public void deleteUser(Long id) {

        userRepository.deleteById(id);
    }

    // Update (PUT) most of the data of a user entity (this applies both to user and admin)
    //  (Role_id can't be changed as user should always be a user)
    public User updateUser(Long id, User user) {

        User existingUser = userRepository.findById(id).orElse(null);

        if (existingUser == null)
        return null;

        existingUser.setName(user.getName() == null ? existingUser.getName() : user.getName());
        existingUser.setEmail(user.getEmail() == null ? existingUser.getEmail() : user.getEmail());
        existingUser.setPassword(user.getPassword() == null ? existingUser.getPassword() : user.getPassword());
        existingUser.setTypeUser(user.getTypeUser() == null ? existingUser.getTypeUser() : user.getTypeUser());
        existingUser.setSizeCompany(user.getSizeCompany() == null ? existingUser.getSizeCompany() : user.getSizeCompany());
        existingUser.setSector(user.getSector() == null ? existingUser.getSector() : user.getSector());
        existingUser.setRegisterDate(user.getRegisterDate() == null ? existingUser.getRegisterDate() : user.getRegisterDate());
        existingUser.setIsBookDownloaded(user.getIsBookDownloaded() == null ? existingUser.getIsBookDownloaded() : user.getIsBookDownloaded());
        existingUser.setIsTestDone(user.getIsTestDone() == null ? existingUser.getIsTestDone() : user.getIsTestDone());       


        return userRepository.save(existingUser);
    }
}