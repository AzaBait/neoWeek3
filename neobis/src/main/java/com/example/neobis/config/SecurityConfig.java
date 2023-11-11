package com.example.neobis.config;

import com.example.neobis.service.impl.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private final UserServiceImpl userService;
    private static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);

    public SecurityConfig(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

        return httpSecurity
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/login").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/user/list")
                        .authenticated()
                        .requestMatchers(HttpMethod.GET, "/api/user/**")
                        .hasAnyRole("ADMIN", "MANAGER", "USER")
                        .requestMatchers(HttpMethod.POST, "/api/user/save")
                        .hasAnyRole("ADMIN", "MANAGER")
                        .requestMatchers(HttpMethod.PUT, "/api/user/**")
                        .hasAnyRole("ADMIN", "MANAGER")
                        .requestMatchers(HttpMethod.DELETE, "/api/user/**").hasRole("ADMIN")
                        .requestMatchers("swagger-ui/**", "/v3/api-docs/**").permitAll())
                .csrf(AbstractHttpConfigurer::disable)
                .build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setUserDetailsService(userService);
        logger.debug("Configuring DaoAuthenticationProvider");
        return authenticationProvider;
    }


}
