package com.muhib.category_service.service;

import com.muhib.category_service.repository.SecurityRepository;
import com.muhib.category_service.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SecurityService {

    @Autowired
    private SecurityRepository userRepository;

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}

