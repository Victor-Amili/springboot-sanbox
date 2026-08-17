package com.victor.postgressql.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.SecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {



        @Bean
        public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
            // CREATE USER AND ROLES
            UserDetails john = User.builder()
                    .username("john")
                    .password("{noop}1234")
                    .roles("EMPLOYEE")
                    .build();

            UserDetails mary = User.builder()
                    .username("mary")
                    .password("{noop}1234")
                    .roles("EMPLOYEE", "MANAGER")
                    .build();

            UserDetails susan = User.builder()
                    .username("susan")
                    .password("{noop}1234")
                    .roles("EMPLOYEE", "MANAGER", "ADMIN")
                    .build();
            return new InMemoryUserDetailsManager(john, mary, susan);
        }

        // restricting access to apis based on roles
        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http)
                throws Exception {

            http.authorizeHttpRequests(configurer ->
                    configurer
                            .requestMatchers(HttpMethod.GET, "/students").hasRole("EMPLOYEE")
                            .requestMatchers(HttpMethod.GET, "/students/**").hasRole("EMPLOYEE")
//                        .requestMatchers(HttpMethod.POST, "/students/**").hasRole("MANAGER")
                            .requestMatchers(HttpMethod.POST, "/students").hasRole("MANAGER")
                            .requestMatchers(HttpMethod.PUT, "/students/**").hasRole("MANAGER")
                            .requestMatchers(HttpMethod.DELETE, "/students/**").hasRole("ADMIN"));

            // using basic auth
            http.httpBasic(Customizer.withDefaults());

            http.csrf(csrf -> csrf.disable());

            return http.build();
        }
}

        
