package com.muhib.service_user.service;


import com.muhib.service_user.entity.User;
import com.muhib.service_user.model.LoginMessage;
import com.muhib.service_user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(11);

    public User registerUser(User userModel) {
        User user = new User();
        user.setEmail(userModel.getEmail());
        user.setName(userModel.getName());
        user.setRole("USER");
        user.setPassword(passwordEncoder.encode(userModel.getPassword()));
        userRepository.save(user);
        return user;
    }

    public User registerAdmin(User userModel) {
        User user = new User();
        user.setEmail(userModel.getEmail());
        user.setName(userModel.getName());
        user.setRole(userModel.getRole());
        user.setPassword(passwordEncoder.encode(userModel.getPassword()));
        userRepository.save(user);
        return user;
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public LoginMessage findByEmailAndPassword(String email, String password) {
        String msg = "";
        User user = userRepository.findByEmail(email);
        if (user != null) {
            String encodedPassword = user.getPassword();

            Boolean isPwdRight = passwordEncoder.matches(password, encodedPassword);
            if (isPwdRight) {
                Optional<User> userModel = userRepository.findOneByEmailAndPassword(email, encodedPassword);
                if (userModel.isPresent()) {
                    return new LoginMessage("Login Success", true, "", user);
                } else {
                    return new LoginMessage("Login Failed", false, "", null);
                }
            } else {
                return new LoginMessage("password Not Match", false, "", null);
            }
        }else {
            return new LoginMessage("Email not exits", false, "", null);
        }

    }
}
