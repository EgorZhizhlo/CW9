package com.rehub.config;

import com.rehub.model.User;
import com.rehub.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {

        boolean adminExists = userRepository.existsByRole(User.Role.ADMIN);

        if (!adminExists) {

            User admin = User.builder()
                    .username("admin")
                    .password(passwordEncoder.encode("admin_adm@"))
                    .role(User.Role.ADMIN)
                    .build();

            userRepository.save(admin);

            System.out.println("\n================= ADMIN CREATED =================");
            System.out.println("Login: admin");
            System.out.println("Password: admin_adm@");
            System.out.println("=================================================\n");
        }
    }
}
