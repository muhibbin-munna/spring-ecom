package com.muhib.category_service.config;

import com.muhib.category_service.security.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@Configuration
@EnableWebSecurity
@EnableWebMvc
public class WebSecurityConfig {


    @Autowired
    private JwtAuthenticationFilter filter;


    private static final String[] WHITE_LIST_URLS = {
            "/",
            "/register",
            "/login",
            "/items"
    };
    private static final String[] NEED_ADMIN_URLS = {
            "/category/add",
            "/item/add/*",
            "/admin/register"
    };

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http.csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        auth -> auth.requestMatchers(WHITE_LIST_URLS).permitAll()
                                .requestMatchers(NEED_ADMIN_URLS).hasAuthority("ADMIN")
                                .anyRequest().authenticated())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class).build();
    }

}

