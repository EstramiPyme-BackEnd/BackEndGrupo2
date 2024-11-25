package com.example.estramipymes.service;

// import java.time.YearMonth;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.estramipymes.exception.ServiceResponse;
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

    public static void assignCurrentFormattedDate(User user) {
        LocalDate currentDate = LocalDate.now();  // Obtiene la fecha actual
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");  // Formato deseado
        user.setRegisterDate(currentDate.format(formatter));  // Asigna la fecha formateada
    }

    // Create (POST) a new user
    public ServiceResponse<User> createUser(User user) {
        User existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser != null) {
            return ServiceResponse.error("El correo electrónico '" + user.getEmail() + "' ya está registrado");
        }

        assignCurrentFormattedDate(user);

        if (user.getIsBookDownloaded() == null) {
            user.setIsBookDownloaded(false);
        }

        if (user.getIsTestDone() == null) {
            user.setIsTestDone(false);
        }

        try {
            user.setPassword(EncryptionUtil.encrypt(user.getPassword()));
        } catch (Exception e) {
            return ServiceResponse.error("Error al encriptar la contraseña");
        }

        User savedUser = userRepository.save(user);
        return ServiceResponse.success(savedUser);
    }
    
    // Remove (DELETE) an existing user
    public void deleteUser(Long id) {

        userRepository.deleteById(id);
    }

    // Update (PUT) most of the data of a user entity (this applies both to user and admin)
    //  (Role_id can't be changed as user should always be a user)
    public ServiceResponse<User> updateUser(Long id, User user) {
        User existingUser = userRepository.findById(id).orElse(null);

        if (existingUser == null) {
            return ServiceResponse.error("Usuario no encontrado");
        }

        if (user.getEmail() != null && !user.getEmail().equals(existingUser.getEmail())) {
            User userWithNewEmail = userRepository.findByEmail(user.getEmail());
            if (userWithNewEmail != null) {
                return ServiceResponse.error("El correo electrónico '" + user.getEmail() + "' ya está en uso por otro usuario");
            }
        }

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
                return ServiceResponse.error("Error al encriptar la contraseña");
            }
        }

        User savedUser = userRepository.save(existingUser);
        return ServiceResponse.success(savedUser);
    }
}
