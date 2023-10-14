package com.medilabosolutions.msfrontend.configuration;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Log4j2
@Configuration
@AllArgsConstructor(onConstructor = @__(@Autowired))
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        log.debug( "securityWebFilterChain() called with {}", httpSecurity);
        httpSecurity
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers( "/").permitAll()
                        .anyRequest().permitAll())
                .formLogin(form -> form.disable())
                .httpBasic(httpBasic -> httpBasic.disable())
                .csrf(csrfSpec -> csrfSpec.disable());

        return httpSecurity.build();
    }


    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager(){
        log.info("inMemoryUserDetailsManager() called - creation of in memory user");
        return new InMemoryUserDetailsManager(
                User
                        .withUsername("user1")
                        .password("{noop}1234")
                        .roles("USER")
                        .build()
        );

    }
}
