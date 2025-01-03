package com.example.demo;

import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class LendingLibraryApplication {
    public static void main(String[] args) {
        SpringApplication.run(LendingLibraryApplication.class, args);
    }

    @Bean
    public CommandLineRunner createDefaultAdmin(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (userRepository.findByUsername("admin").isEmpty()) {
                User admin = new User();
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("admin123")); // ✅ Ensure password is hashed
                admin.setRole(Role.ADMIN);
                userRepository.save(admin);
                System.out.println("✅ Admin user created: admin / admin123");
            } else {
                System.out.println("✅ Admin user already exists");
            }
        };
    }
}