package com.dev.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.context.annotation.Bean;

@Configuration
public class SecurityConfig {
    
    @Bean
    public SecurityFilterChain apiSecurity(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests((auth) -> auth
                .requestMatchers("/api/main/**").permitAll()
                .anyRequest()
                .authenticated()
        )
                .httpBasic()
                .and()
                .csrf().disable() // Désactivez CSRF pour faciliter les tests, mais assurez-vous de l'activer en production.
                .cors();
        return http.build();
    }
}
