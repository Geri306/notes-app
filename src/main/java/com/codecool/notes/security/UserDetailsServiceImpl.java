//package com.codecool.notes.security;
//
//import com.codecool.notes.api.exception.validation.EmailNotFoundException;
//import com.codecool.notes.persistence.entity.User;
//import com.codecool.notes.persistence.entity.UserRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.Collections;
//import java.util.Optional;
//
//@Service
//@RequiredArgsConstructor
//public class UserDetailsServiceImpl implements UserDetailsService {
//
//    private final UserRepository userRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        Optional<User> oUser = userRepository.findByEmail(email);
//        if (oUser.isEmpty()) {
//            throw new UsernameNotFoundException("User not found");
//        }
//
//        User user = oUser.get();
//
//        return new org.springframework.security.core.userdetails.User(
//                user.getEmail(),
//                user.getPassword(),
//                Collections.emptyList()
//        );
//    }
//}
//
