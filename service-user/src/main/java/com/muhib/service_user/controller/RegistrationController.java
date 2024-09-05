package com.muhib.service_user.controller;

import com.muhib.service_user.entity.User;
import com.muhib.service_user.model.LoginMessage;
import com.muhib.service_user.security.JwtHelper;
import com.muhib.service_user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
public class RegistrationController {
    @Autowired
    private UserService userService;

    @Autowired
    private JwtHelper helper;

    @GetMapping("/")
    public String hello(){
        return "Hello World!";
    }

    @GetMapping("/home")
    public String helloWorld(){
        return "Hello World Home!";
    }

    @PostMapping("/register")
    public String registerUser(@RequestBody User userModel, final HttpServletRequest request) {
        User user = userService.registerUser(userModel);
        return "Success";
    }

    @PostMapping("/admin/register")
    public String registerAdmin(@RequestBody User userModel, final HttpServletRequest request) {
        User user = userService.registerAdmin(userModel);
        return "Success";
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User userModel, final HttpServletRequest request) {
        LoginMessage loginMessage = userService.findByEmailAndPassword(userModel.getEmail(), userModel.getPassword());
        if(loginMessage.getStatus()){
            String token = this.helper.generateToken(loginMessage.getUser());
            loginMessage.setToken(token);
        }
        Map<String, String> map = new HashMap<>();
        map.put("Status", loginMessage.getMessage());
        if(loginMessage.getStatus()){
            map.put("Token", loginMessage.getToken());
        }
        return ResponseEntity.ok(map);
    }

}
