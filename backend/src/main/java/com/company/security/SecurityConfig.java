package com.company.security;

import com.company.service.CustomUserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.company.config.CorsConfig;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomUserDetailService customUserDetailsService;
    private final JwtFilter jwtFilter;
    private final CorsConfig corsConfig;

    public SecurityConfig(CustomUserDetailService customUserDetailsService, JwtFilter jwtFilter, CorsConfig corsConfig) {
        this.customUserDetailsService = customUserDetailsService;
        this.jwtFilter = jwtFilter;
        this.corsConfig = corsConfig;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors.configurationSource(corsConfig.corsConfigurationSource())) // ✅ Pastikan CORS diaktifkan
                .csrf(csrf -> csrf.disable()) // ✅ Nonaktifkan CSRF jika pakai JWT
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers(HttpMethod.POST, "/api/auth/signup", "/api/auth/signin", "/api/auth/signout").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/api/user/profile").authenticated()
                        .requestMatchers(HttpMethod.GET, "/api/auth/profile").authenticated()
                        .requestMatchers(HttpMethod.POST, "/api/auth/forgot-password").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/content/all").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/content/*").permitAll()  // ✅ Perbaikan wildcard
                        .requestMatchers(HttpMethod.POST, "/api/content/create").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/api/content/**").authenticated()  // ✅ Perbaikan wildcard
                        .requestMatchers(HttpMethod.DELETE, "/api/content/**").authenticated()  // ✅ Perbaikan wildcard
                        .requestMatchers(HttpMethod.PUT, "/api/content/**/publish").authenticated()  // ✅ Perbaikan wildcard
                        .requestMatchers("/api/admin/**").authenticated() // ✅ Admin harus login
                        .requestMatchers("/api/auth/**").permitAll() // ✅ Public API untuk autentikasi
                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        return authenticationManagerBuilder.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();  // ✅ Enkripsi password dengan BCrypt
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
