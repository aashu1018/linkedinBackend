package com.springboot.linkedin.user_service.controller;

import com.springboot.linkedin.user_service.dto.LoginRequestDTO;
import com.springboot.linkedin.user_service.dto.SignupRequestDTO;
import com.springboot.linkedin.user_service.dto.UserDTO;
import com.springboot.linkedin.user_service.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<UserDTO> signup(@RequestBody SignupRequestDTO requestDTO){
        UserDTO userDTO = authService.signup(requestDTO);
        return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDTO requestDTO){
        String token = authService.login(requestDTO);
        return ResponseEntity.ok(token);
    }
}
