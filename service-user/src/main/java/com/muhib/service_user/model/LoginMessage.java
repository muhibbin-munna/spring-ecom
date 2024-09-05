package com.muhib.service_user.model;

import com.muhib.service_user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginMessage {
    String message;
    Boolean status;
    String token;
    User user;

}