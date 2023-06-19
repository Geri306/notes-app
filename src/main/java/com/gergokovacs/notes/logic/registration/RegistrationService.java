package com.gergokovacs.notes.logic.registration;

import com.gergokovacs.notes.persistence.entity.User;
import com.gergokovacs.notes.persistence.entity.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class RegistrationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void register(Map<String, String> credentials, boolean asAdmin) {
        User user = new User();
        user.setEmail(credentials.get("email"));
        user.setPassword(passwordEncoder.encode(credentials.get("password")));
        Set<String> authorities = new HashSet<>(Set.of("USER"));
        if (asAdmin) {
            authorities.add("ADMIN");
        }
        user.setAuthorities(authorities);
        userRepository.save(user);
    }
}
