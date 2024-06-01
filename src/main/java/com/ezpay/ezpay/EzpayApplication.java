package com.ezpay.ezpay;

import com.ezpay.ezpay.domains.entity.Company;
import com.ezpay.ezpay.domains.entity.User;
import com.ezpay.ezpay.domains.enums.Currency;
import com.ezpay.ezpay.domains.enums.Role;
import com.ezpay.ezpay.repository.CompanyRepository;
import com.ezpay.ezpay.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@EnableAsync
@SpringBootApplication
public class EzpayApplication implements CommandLineRunner {
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final CompanyRepository companyRepository;

    public EzpayApplication(UserRepository userRepository, PasswordEncoder passwordEncoder, CompanyRepository companyRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
		this.companyRepository = companyRepository;
	}

    public static void main(String[] args) {
		SpringApplication.run(EzpayApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		if(!userRepository.existsByUsername("user")) {
			User user = new User();
			user.setUsername("user");
			user.setPassword(passwordEncoder.encode("pass"));
			user.setRole(Role.ROLE_USER);
			user.setPhone("9090");
			user.setAvatarUrl("https://st3.depositphotos.com/15648834/17930/v/450/depositphotos_179308454-stock-illustration-unknown-person-silhouette-glasses-profile.jpg");
			user.setCreateDate(LocalDateTime.now());
			user.setIsActive(true);

			user = userRepository.save(user);

			Company save = companyRepository.save(
					Company.builder()
							.email("email")
							.companyName("company")
							.businessType("TTT")
							.currency(Currency.USD)
							.addressCompany("1234 Example Street, Example City, EX 12345")
							.statementDescription("Example Statement Description")
							.taxId("taxId")
							.supportPhone("supportPhone")
							.createDate(LocalDateTime.now())
							.price(new BigDecimal("99.99"))
							.user(user)
							.build()
			);

			System.out.println("saved company" + save);
		}

	}
}
