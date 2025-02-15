package com.company.security;

import com.company.service.CustomUserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomUserDetailService customUserDetailsService;
    private final JwtFilter jwtFilter;

    public SecurityConfig(CustomUserDetailService customUserDetailService, JwtFilter jwtFilter) {
        this.customUserDetailsService = customUserDetailService;
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf
                        .disable())  // Disabling CSRF for REST APIs (adjust as needed for your use case)
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers(HttpMethod.POST, "/api/auth/signup", "/api/auth/signin", "/api/auth/signout").permitAll()  // Allow sign up and sign in without authentication
                        .requestMatchers(HttpMethod.GET, "/api/user/profile").permitAll()  // Endpoint untuk profile harus diizinkan
                        .requestMatchers(HttpMethod.PUT, "/api/user/profile").authenticated()  // Profile access should be authenticated
                        .requestMatchers(HttpMethod.GET, "/api/auth/profile").authenticated()  // Profile access should be authenticated
                        .requestMatchers(HttpMethod.POST, "/api/auth/forgot-password").permitAll()  // Allow forgot-password endpoint without authentication
                        .requestMatchers(HttpMethod.GET, "/api/content/all").permitAll()  // Endpoint untuk profile harus diizinkan
                        .requestMatchers(HttpMethod.GET, "/api/content/contentId").permitAll()  // Endpoint untuk profile harus diizinkan
                        .anyRequest().authenticated()  // All other requests require authentication
                )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);  // Add JWT filter to the chain

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        return authenticationManagerBuilder.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();  // Use BCryptPasswordEncoder for encoding passwords
    }
}



/*
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/auth/signup", "/api/auth/signin", "/api/auth/forgot-password").permitAll()  // Publik endpoint
                .anyRequest().authenticated()  // Semua permintaan lainnya harus terautentikasi
                .and()
                .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .and()
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);  // Menambahkan filter JWT

        return http.build();
    }
}
*/
