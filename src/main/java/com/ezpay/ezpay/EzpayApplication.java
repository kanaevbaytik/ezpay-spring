package com.ezpay.ezpay;

import com.ezpay.ezpay.domains.entity.User;
import com.ezpay.ezpay.domains.enums.Role;
import com.ezpay.ezpay.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.time.LocalDateTime;

@SpringBootApplication
public class EzpayApplication implements CommandLineRunner {
	private final UserRepository userRepository;

    public EzpayApplication(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public static void main(String[] args) {
		SpringApplication.run(EzpayApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		if(!userRepository.existsByUsername("user")) {
			User user = new User();
			user.setUsername("user");
			user.setPassword("pass");
			user.setRole(Role.ROLE_USER);
			user.setPhone("9090");
			user.setAvatarUrl("https://st3.depositphotos.com/15648834/17930/v/450/depositphotos_179308454-stock-illustration-unknown-person-silhouette-glasses-profile.jpg");
			user.setCreateDate(LocalDateTime.now());
			user.setIsActive(true);
		}

	}
}
