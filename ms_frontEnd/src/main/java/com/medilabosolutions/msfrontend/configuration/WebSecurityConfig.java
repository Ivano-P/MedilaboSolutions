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

    private final PasswordEncoder passwordEncoder;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        log.debug("securityWebFilterChain() called with {}", httpSecurity);
        httpSecurity
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/styles.css").permitAll()
                        .requestMatchers("/patients", "/informations", "/updateInfo" , "/updatePatient",
                                "/addPatient", "/addPatient" , "/updateHistory").hasRole("USER").anyRequest()
                        .authenticated())
                .formLogin(form -> form.defaultSuccessUrl("/patients")) //aller a cette page apres connexion
                .logout(auth -> auth.logoutUrl("/logout") // pour notre boutton de déconnexion
                        .logoutSuccessUrl("/") // Invalider la session HTTP
                        .invalidateHttpSession(true).deleteCookies("JSESSIONID"))// Supprimer le cookie de session
                .csrf(csrfSpec -> csrfSpec.disable());

        return httpSecurity.build();
    }


    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
        log.info("inMemoryUserDetailsManager() called - creation of in memory user");
        return new InMemoryUserDetailsManager(
                User
                        .withUsername("userP")
                        .password(passwordEncoder.encode(System.getenv("TEST_USER_PWD")))
                        .roles("USER")
                        .build(),

                User.withUsername("userO")
                        .password(passwordEncoder.encode(System.getenv("TEST_USER_PWD")))
                        .roles("USER")
                        .build()
        );

    }


}
