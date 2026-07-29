package com.example.spring_boot_project.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.userdetails.User;
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests((authorize)->{
                    authorize.requestMatchers("/error").permitAll();
                    authorize.requestMatchers(HttpMethod.DELETE, "/users/**").hasRole("ADMIN");
                    authorize.requestMatchers("/users/**").hasAnyRole("ADMIN", "USER");
                    authorize.anyRequest().authenticated();
                })
                .formLogin(form -> form
                        .loginPage("/login") 
                        .permitAll() 
                        .defaultSuccessUrl("/users", true)
                );
        return http.build();
    }

    /*
    @Bean
    public UserDetailsService userDetailsService(){

        UserDetails username = User.builder()
                .username("username")
                .password(passwordEncoder().encode("username"))
                .roles("USER")
                .build();

        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("admin"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(username,admin);
    }
        */
}
