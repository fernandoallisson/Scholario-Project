package com.scholario.scholario_demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .csrf(csrf -> csrf.disable())  // Desabilitar CSRF para APIs REST
        .authorizeHttpRequests(authorizeRequests -> authorizeRequests
            .anyRequest().authenticated() // Exigir autenticação para qualquer outra rota
        ).httpBasic();

    return http.build();
  }
}
