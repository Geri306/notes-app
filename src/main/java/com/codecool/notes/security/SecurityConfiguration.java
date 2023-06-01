package com.codecool.notes.security;

//import com.codecool.notes.configuration.CorsFilterConfig;
import com.codecool.notes.persistence.entity.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfiguration {


    private final AppBasicAuthenticationEntryPoint authenticationEntryPoint;
//    private final CorsFilterConfig corsFilterConfig;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
//                .cors(cors -> corsFilterConfig.corsFilter())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/v1/notes","/login", "/register").permitAll()
//                        .requestMatchers("/api/v1/notes")
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults())
//                .authenticationManager(authentication -> authenticationEntryPoint())
//                .authenticationProvider(authenticationEntryPoint)
                .sessionManagement((session) -> session.sessionCreationPolicy(STATELESS))
//                .exceptionHandling(entry -> authenticationEntryPoint())
                .build();
    }

    @Bean
    UserDetailsService userDetailsService(UserRepository userRepository) {
        return username -> userRepository.findByEmail(username)
                .map(UserPrincipal::new)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationEntryPoint unauthorizedEntryPoint() {
        return (request, response, authException) -> {
            System.err.println("ENTERED");
            response.setHeader("WWW-Authenticate", "Basic realm=\"example\"");
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorizeddd");
        };
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    TokenGenerator tokenGenerator() {
        return new TokenGenerator();
    }
}
