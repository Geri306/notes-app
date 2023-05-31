package com.codecool.notes.runner;

import com.codecool.notes.persistence.entity.User;
import com.codecool.notes.persistence.entity.UserRepository;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "defaultusers")
@Setter
@Slf4j
public class UsersPopulator {

    @Value("${app.loadDefaultUsers:false}")
    private boolean loadDefaultUsers;
    private List<User> admins;
    private List<User> users;

    @Bean
    ApplicationRunner populator(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (loadDefaultUsers) {
                User admin = new User();
                User user = new User();

                admin.setEmail(admins.get(0).getEmail());
                admin.setPassword(passwordEncoder.encode(admins.get(0).getPassword()));
                admin.setAuthorities(admins.get(0).getAuthorities());

                user.setEmail(users.get(0).getEmail());
                user.setPassword(passwordEncoder.encode(users.get(0).getPassword()));
                user.setAuthorities(users.get(0).getAuthorities());

                userRepository.saveAll(List.of(admin, user));
                userRepository.findAll().forEach(savedUser -> log.info("Preloaded " + savedUser.getEmail() + ", ROLES: " + savedUser.getAuthorities()));
            }
        };
    }
}
