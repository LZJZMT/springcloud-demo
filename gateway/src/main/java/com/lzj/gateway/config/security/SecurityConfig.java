package com.lzj.gateway.config.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {
    
    @Bean
    SecurityWebFilterChain webFluxSecurityFilterChain(ServerHttpSecurity http) {
        http.authorizeExchange()
            .pathMatchers("/doc.html")
            .hasRole("admin")
            .anyExchange()
            .permitAll()
            .and()
            .httpBasic()
            .and()
            .csrf()
            .disable();
        return http.build();
    }
}
