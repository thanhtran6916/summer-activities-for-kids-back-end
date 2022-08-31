package com.example.backend.service;

import com.example.backend.dto.UserDTO;
import com.example.backend.dto.request.UserRequest;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    UserDTO findByUsername(String username);

    UserDTO registerUser(UserRequest userRequest);

    UserDTO changeUsernameAndPassword(UserRequest userRequest);
}
