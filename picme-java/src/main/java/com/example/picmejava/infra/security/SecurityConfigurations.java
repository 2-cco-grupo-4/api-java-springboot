package com.example.picmejava.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations implements WebMvcConfigurer {

    @Autowired
    private SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return http.
                csrf(csrf -> csrf.disable())
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .cors()
                .and()
                .authorizeHttpRequests(req -> {
                    req.requestMatchers(HttpMethod.POST, "/login").permitAll();
                    req.requestMatchers(HttpMethod.POST, "/usuario/**").permitAll();
                    req.requestMatchers(HttpMethod.POST, "/imagens/**").permitAll();
                    req.requestMatchers(HttpMethod.GET, "/imagens/**").permitAll();
                    req.requestMatchers(HttpMethod.GET, "/s3/**").permitAll();
                    req.requestMatchers(HttpMethod.POST, "/s3/**").permitAll();
                    req.requestMatchers(HttpMethod.GET, "/swagger-ui/**").permitAll();
                    req.requestMatchers(HttpMethod.GET, "/v3/api-docs/**").permitAll();
                    req.requestMatchers(HttpMethod.POST, "/clientes/cadastrar").permitAll();
                    req.requestMatchers(HttpMethod.POST, "/fotografos/cadastrar").permitAll();
                    req.requestMatchers(HttpMethod.GET, "/fotografos/**").hasRole("FOTOGRAFO");
                    req.requestMatchers(HttpMethod.POST, "/fotografos/**").hasRole("FOTOGRAFO");
                    req.requestMatchers(HttpMethod.PATCH, "/fotografos/**").hasRole("FOTOGRAFO");
                    req.requestMatchers(HttpMethod.GET, "/eventos").hasAnyRole("FOTOGRAFO", "ADMIN");
                    req.requestMatchers(HttpMethod.POST, "/eventos/contrato").permitAll();
                    req.requestMatchers(HttpMethod.PUT, "/eventos/contrato/{idContrato}").permitAll();
                    req.requestMatchers(HttpMethod.PUT, "/eventos/pagamento/{idPagamento}").permitAll();
                    req.requestMatchers(HttpMethod.GET, "/eventos/pagamento/{idPagamento}").permitAll();
                    req.requestMatchers(HttpMethod.GET, "/eventos/{idSessao}").permitAll();
                    req.requestMatchers(HttpMethod.GET, "/instagram/**").permitAll();
                    req.requestMatchers(HttpMethod.POST, "/instagram/**").permitAll();
                    req.requestMatchers(HttpMethod.POST, "/enderecos/**").permitAll();
                    req.requestMatchers(HttpMethod.GET, "/enderecos/**").permitAll();
                    req.requestMatchers(HttpMethod.PUT, "/enderecos/**").permitAll();
//                    req.requestMatchers(HttpMethod.POST, "/imagens").hasAnyRole("FOTOGRAFO", "ADMIN");
//                    req.requestMatchers(HttpMethod.PUT, "/imagens").hasAnyRole("FOTOGRAFO", "ADMIN");
                    req.requestMatchers(HttpMethod.POST, "/albums").hasAnyRole("FOTOGRAFO", "ADMIN");
                    req.requestMatchers(HttpMethod.POST, "/temas").hasAnyRole("FOTOGRAFO", "ADMIN");
                    req.requestMatchers(HttpMethod.GET, "/temas").permitAll();
                    req.requestMatchers(HttpMethod.GET, "/temas/pesquisar").permitAll();
                    req.requestMatchers(HttpMethod.GET, "/arquivo/**").hasAnyRole("ADMIN", "FOTOGRAFO");
                    req.requestMatchers(HttpMethod.POST, "/arquivo/**").hasRole("ADMIN");
                    req.requestMatchers(HttpMethod.POST, "/clientes/cadastrarExterno").hasRole("FOTOGRAFO");
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
                .allowedOrigins("https://picme.app.br", "http://localhost:3000", "*", "**")
                .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS", "HEAD", "TRACE", "CONNECT");
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(12);
    }
}
