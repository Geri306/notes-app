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

    @Value("${app.loadDefaultUsers}")
    private boolean loadDefaultUsers;
    private List<User> users;

    @Bean
    ApplicationRunner populateUsers(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (!loadDefaultUsers) {
                return;
            }
            users.stream()
                    .map(u -> u.setPassword(passwordEncoder.encode(u.getPassword())))
                    .forEach(userRepository::save);
            userRepository.findAll()
                    .forEach(user -> log.info("Preloaded " + user.getEmail() + ", ROLES: " + user.getAuthorities()));
        };
    }
}
