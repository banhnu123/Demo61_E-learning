package com.data.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ConfigSercurity {

    @Bean
    public PasswordEncoder passwordEncoder() {
        //ko ma hoa
        return NoOpPasswordEncoder.getInstance();

        //Duoc ma hoa
//        return new BCryptPasswordEncoder();
    }

    // config role
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

                http.csrf().disable();

                http.authorizeHttpRequests().requestMatchers("/products/**").hasAnyRole("ADMIN");
                http.authorizeHttpRequests().requestMatchers("/account/**").hasAnyRole("CUSTOMER");
              //  http.authorizeHttpRequests().requestMatchers("/products/**").permitAll();//cho phep tat ca

        return http.httpBasic().and().build();
    }

}
