package com.fatihblog.api.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((auth) -> auth
//                        .antMatchers("/api/admin/**").hasRole("ADMIN")
//                        .antMatchers("/api/user/**").hasRole("USER")
                                .anyRequest().permitAll()
                );
        http.anonymous();
        http.cors().disable();
        http.csrf().disable();

        return http.build();
    }
}
