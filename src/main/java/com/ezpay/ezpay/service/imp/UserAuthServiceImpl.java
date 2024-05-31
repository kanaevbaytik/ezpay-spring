package com.ezpay.ezpay.service.imp;

import com.ezpay.ezpay.domains.dto.request.UserSignInDtoRequest;
import com.ezpay.ezpay.domains.dto.request.UserSignUpDtoRequest;
import com.ezpay.ezpay.domains.dto.response.SignInDtoResponse;
import com.ezpay.ezpay.domains.entity.User;
import com.ezpay.ezpay.domains.enums.Role;
import com.ezpay.ezpay.exception.UserAlreadyExist;
import com.ezpay.ezpay.repository.UserRepository;
import com.ezpay.ezpay.security.JwtService;
import com.ezpay.ezpay.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserAuthServiceImpl implements AuthService<UserSignInDtoRequest, UserSignUpDtoRequest> {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserAuthServiceImpl(UserRepository userRepository, JwtService jwtService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @Override
    public String signUp(UserSignUpDtoRequest userRequest) {
        Optional<User> account = userRepository.findByUsername(userRequest.getUsername());
        System.out.println("asdsadadasdasda"+account);
        if (account.isPresent()) {
            throw new UserAlreadyExist(HttpStatus.CONFLICT, "User already exist:" + userRequest.getUsername());
        } else {
            User user = User.builder()
                    .username(userRequest.getUsername())
                    .password(passwordEncoder.encode(userRequest.getPassword()))
                    .phone(userRequest.getPhone())
                    .isActive(true)
                    .role(Role.ROLE_USER)
                    .createDate(LocalDateTime.now())
                    .build();
            System.out.println("users"+user);
            userRepository.save(user);
            return jwtService.generateToken(user);
              }
        }
    @Override
    public String signIn(UserSignInDtoRequest user) {
        User us = userRepository.findByUsername(user.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("user not found!"));
        return jwtService.generateToken(us);
    }

    @Override
    public SignInDtoResponse getMe(String token) {
        User user = userRepository.findByUsername(jwtService.extractUsername(token))
                .orElseThrow(() -> new UsernameNotFoundException("user not found!"));

        return SignInDtoResponse.builder()
                .username(user.getUsername())
                .phone(user.getPhone())
                .avatarUrl(user.getAvatarUrl())
                .build();
    }
}
