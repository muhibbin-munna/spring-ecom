package com.muhib.common_util.service;

import com.muhib.common_util.entity.User;
import com.muhib.common_util.repository.SecurityRepository;
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

