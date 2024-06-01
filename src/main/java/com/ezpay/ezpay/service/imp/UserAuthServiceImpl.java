package com.ezpay.ezpay.service.imp;

import com.ezpay.ezpay.domains.dto.request.PasswordResetRequest;
import com.ezpay.ezpay.domains.dto.request.UserSignInDtoRequest;
import com.ezpay.ezpay.domains.dto.request.UserSignUpDtoRequest;
import com.ezpay.ezpay.domains.dto.response.SignInDtoResponse;
import com.ezpay.ezpay.domains.entity.PasswordReset;
import com.ezpay.ezpay.domains.entity.User;
import com.ezpay.ezpay.domains.enums.Role;
import com.ezpay.ezpay.exception.UserAlreadyExist;
import com.ezpay.ezpay.repository.PasswordResetRepo;
import com.ezpay.ezpay.repository.UserRepository;
import com.ezpay.ezpay.security.JwtService;
import com.ezpay.ezpay.service.AuthService;
import com.ezpay.ezpay.service.MailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserAuthServiceImpl implements AuthService<UserSignInDtoRequest, UserSignUpDtoRequest> {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final PasswordResetRepo passwordResetRepo;
    private final MailSender mailSender;

    @Autowired
    public UserAuthServiceImpl(UserRepository userRepository, JwtService jwtService, PasswordEncoder passwordEncoder, PasswordResetRepo passwordResetRepo, MailSender mailSender) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.passwordResetRepo = passwordResetRepo;
        this.mailSender = mailSender;
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
                    .avatarUrl("https://images.pexels.com/photos/771742/pexels-photo-771742.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500 ")
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
        System.out.println(user.getUsername());
        User us = userRepository.findByUsername(user.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("user not found!"));

        if(passwordEncoder.matches(user.getPassword(), us.getPassword())) {
            return jwtService.generateToken(us);
        }
        throw new UsernameNotFoundException("no such user");
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

    @Override
    public String resetPassword(String code) {
        PasswordReset passwordReset = passwordResetRepo.findByCode(code)
                .orElseThrow(() -> new UsernameNotFoundException("user not found"));

        User user = userRepository.findByUsername(passwordReset.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("user not found"));

        String newPassword = UUID.randomUUID().toString();
        user.setPassword(passwordEncoder.encode(newPassword));

        userRepository.save(user);
        passwordResetRepo.delete(passwordReset);

        mailSender.send(user.getUsername(), newPassword );
        return "<h3>The new password sent to your email</h3>";
    }
}
