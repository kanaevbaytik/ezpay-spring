package com.ezpay.ezpay.service.imp;

import com.ezpay.ezpay.domains.dto.request.UserSignInDtoRequest;
import com.ezpay.ezpay.domains.dto.request.UserSignUpDtoRequest;
import com.ezpay.ezpay.domains.dto.response.SignInDtoResponse;
import com.ezpay.ezpay.domains.entity.User;
import com.ezpay.ezpay.domains.enums.Status;
import com.ezpay.ezpay.exception.UserAlreadyExist;
import com.ezpay.ezpay.repository.UserRepository;
import com.ezpay.ezpay.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserAuthServiceImpl implements AuthService<UserSignInDtoRequest, UserSignUpDtoRequest> {
    private final UserRepository userRepository;

    @Autowired
    public UserAuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public String signUp(UserSignUpDtoRequest userRequest) {
        Optional<User> account = userRepository.findByUsername(userRequest.getUsername());
        if (account.isPresent()) {
            throw new UserAlreadyExist(HttpStatus.CONFLICT, "User already exist:" + userRequest.getUsername());
        } else {
            User user = User.builder()
                    .username(userRequest.getFirstName())
                    .password(userRequest.getLastName())
                    .phone(userRequest.getPhone())
                    .isActive(Status.Active)
                    .createDate(LocalDateTime.now())
                    .build();
            return "";
        }
    }
    @Override
    public String signIn(UserSignInDtoRequest user) {
        return "";
    }

    @Override
    public SignInDtoResponse getMe(String token) {
        return null;
    }
}
