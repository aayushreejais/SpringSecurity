package com.springSecurity.security.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
//@EnableMethodSecurity
public class SecurityConfig {

  @Autowired
  private JwtAuthenticationEntryPoint point;
  @Autowired
  private AuthenticationFilter filter;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

    http.csrf(csrf -> csrf.disable())
        .authorizeRequests().
        requestMatchers("/home/**")
        .authenticated()
        //for role based authentication for some specific url we can add .hasRole and the other way to do it on the controller to put @PreAuthorize ->@PreAuthorize("hasRole('USER')") also add @EnableMethodSecurity annotation on the security config so to enable the preauthorize
//        .hasRole("ADMIN")
        .requestMatchers("/auth/login").permitAll()
        .anyRequest()
        .authenticated()
        .and()
        .exceptionHandling(ex -> ex.authenticationEntryPoint(point))
        .sessionManagement(
            session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
    http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
    return http.build();
  }

}
