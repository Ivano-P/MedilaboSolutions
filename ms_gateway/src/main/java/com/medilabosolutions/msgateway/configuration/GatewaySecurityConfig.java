package com.medilabosolutions.msgateway.configuration;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Log4j2
@Configuration
@EnableWebSecurity
public class GatewaySecurityConfig{

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        log.debug("securityFilterChain() called with: {}", httpSecurity );

        httpSecurity
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/login").permitAll()
                        .requestMatchers("/gestion/patients", "/gestion/info", "/gestion/update",
                                "/gestion/add","/historique/noteById", "/historique/noteByName",
                                "/historique/updateNotes", "/risque/evaluate")
                        .authenticated())
                .formLogin(auth -> auth.defaultSuccessUrl("http://localhost:9001/patients", true))
                .logout(auth -> auth.logoutSuccessUrl("http://localhost:9001/")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID"))
                .csrf(csrf -> csrf.disable())
        ;

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
