package com.medilabosolutions.msgestionpatient.security;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Log4j2
@Configuration
@EnableWebSecurity
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class SecurityConfig {


    private final BCryptPasswordEncoder passwordEncoder;

    /*
    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager(){
        log.info("inMemoryUserDetailsManager() called - creation of in memory user");
        return new InMemoryUserDetailsManager(
                User
                        .withUsername("user1")
                        .password(passwordEncoder.encode(System.getenv("TEST_USER_ONE")))
                        .roles("USER", "ADMIN")
                        .build()
        );

    }
    */

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        log.debug("securityFilterChain() called with: {}", httpSecurity );

        httpSecurity
                .csrf(auth -> auth.disable())
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll())
                .formLogin(auth -> auth.disable())
                .logout(auth -> auth.disable())
        ;

        return httpSecurity.build();
    }
}

