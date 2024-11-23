package com.example.estramipymes.service;

// import java.time.YearMonth;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.estramipymes.model.User;
import com.example.estramipymes.repository.UserRepository;
import com.example.estramipymes.util.EncryptionUtil;

@Service
public class UserService {
  
     @Autowired
    private UserRepository userRepository;

    // Read (GET) all the users
    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();
        users.forEach(user -> {
            try {
                user.setPassword(user.getDecryptedPassword());
            } catch (Exception ignored) {
            }
        });
        return users;
    }

    // Read (GET) a user by email
    public User getUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            try {
                user.setPassword(user.getDecryptedPassword());
            } catch (Exception ignored) {
            }
        }
        return user;
    }

    // Read (GET) a user by ID
    public User getUser(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            try {
                user.setPassword(user.getDecryptedPassword());
            } catch (Exception ignored) {
            }
        }
        return user;
    }

    // Create (POST) a new user
    public User createUser(User user) {
        User existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser != null)
            return null;

        // YearMonth currentMonth = YearMonth.now();
        user.setRegisterDate("2024-11");

        if (user.getIsBookDownloaded() == null) {
            user.setIsBookDownloaded(false);
        }

        if (user.getIsTestDone() == null) {
            user.setIsTestDone(false);
        }
        // Encriptar la contraseña
        try {
            user.setPassword(EncryptionUtil.encrypt(user.getPassword())); // Encriptar contraseña
        } catch (Exception e) {
            throw new RuntimeException("Error encrypting password", e);
        }
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
        existingUser.setTypeUser(user.getTypeUser() == null ? existingUser.getTypeUser() : user.getTypeUser());
        existingUser.setSizeCompany(user.getSizeCompany() == null ? existingUser.getSizeCompany() : user.getSizeCompany());
        existingUser.setSector(user.getSector() == null ? existingUser.getSector() : user.getSector());
        existingUser.setRegisterDate(user.getRegisterDate() == null ? existingUser.getRegisterDate() : user.getRegisterDate());
        existingUser.setIsBookDownloaded(user.getIsBookDownloaded() == null ? existingUser.getIsBookDownloaded() : user.getIsBookDownloaded());
        existingUser.setIsTestDone(user.getIsTestDone() == null ? existingUser.getIsTestDone() : user.getIsTestDone());    
        if (user.getPassword() != null) {
            try {
                existingUser.setPassword(EncryptionUtil.encrypt(user.getPassword()));
            } catch (Exception e) {
                throw new RuntimeException("Error encrypting password", e);
            }
        }
        return userRepository.save(existingUser);
    }
}
