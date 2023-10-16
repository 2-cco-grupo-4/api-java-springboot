package com.example.picmejava.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations implements WebMvcConfigurer {

    @Autowired
    private SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return http.csrf(csrf -> csrf.disable())
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .cors().and()
                .authorizeHttpRequests(req -> {
                    req.requestMatchers(HttpMethod.POST, "/login").permitAll();
                    req.requestMatchers(HttpMethod.POST, "/usuario/**").permitAll();
                    req.requestMatchers(HttpMethod.GET, "/swagger-ui/**").permitAll();
                    req.requestMatchers(HttpMethod.GET, "/v3/api-docs/**").permitAll();
                    req.requestMatchers(HttpMethod.POST, "/clientes/cadastrar").permitAll();
                    req.requestMatchers(HttpMethod.POST, "/fotografos/cadastrar").permitAll();
                    req.requestMatchers(HttpMethod.GET, "/eventos").hasAnyRole("FOTOGRAFO", "ADMIN");
                    req.requestMatchers(HttpMethod.GET, "/instagram/**").hasAnyRole("FOTOGRAFO", "ADMIN");
                    req.requestMatchers(HttpMethod.POST, "/instagram/**").hasAnyRole("FOTOGRAFO", "ADMIN");
                    req.requestMatchers(HttpMethod.POST, "/imagens").hasAnyRole("FOTOGRAFO", "ADMIN");
                    req.requestMatchers(HttpMethod.PUT, "/imagens").hasAnyRole("FOTOGRAFO", "ADMIN");
                    req.requestMatchers(HttpMethod.POST, "/albums").hasAnyRole("FOTOGRAFO", "ADMIN");
                    req.requestMatchers(HttpMethod.POST, "/temas").hasAnyRole("FOTOGRAFO", "ADMIN");
                    req.requestMatchers("/admin/**").hasRole("ADMIN");
                    req.anyRequest().authenticated();
                })
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception{
        return configuration.getAuthenticationManager();
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD", "TRACE", "CONNECT");
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(12);
    }
}
