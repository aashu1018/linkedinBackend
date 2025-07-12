package com.springboot.linkedin.user_service.service;

import com.springboot.linkedin.user_service.dto.LoginRequestDTO;
import com.springboot.linkedin.user_service.dto.SignupRequestDTO;
import com.springboot.linkedin.user_service.dto.UserDTO;
import com.springboot.linkedin.user_service.entity.User;
import com.springboot.linkedin.user_service.exception.BadRequestException;
import com.springboot.linkedin.user_service.exception.ResourceNotFoundException;
import com.springboot.linkedin.user_service.repository.UserRepository;
import com.springboot.linkedin.user_service.utils.PasswordUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final JWTService jwtService;

    public UserDTO signup(SignupRequestDTO requestDTO) {
        User user = modelMapper.map(requestDTO, User.class);
        user.setPassword(PasswordUtil.hashPassword(requestDTO.getPassword()));

        User savedUser = userRepository.save(user);
        return modelMapper.map(savedUser, UserDTO.class);
    }

    public String login(LoginRequestDTO requestDTO) {
        User user = userRepository.findByEmail(requestDTO.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + requestDTO.getEmail()));

       boolean isPasswordMatch = PasswordUtil.checkPassword(requestDTO.getPassword(), user.getPassword());
       if(!isPasswordMatch){
           throw new BadRequestException("Incorrect password!");
       }

       return jwtService.generateAccessToken(user);
    }
}
