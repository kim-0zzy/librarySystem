package com.example.libraryProject.Security.config;

import com.example.libraryProject.Security.handler.CustomAuthenticationFailureHandler;
import com.example.libraryProject.Security.handler.CustomAuthenticationSuccessHandler;
import com.example.libraryProject.Security.handler.CustomLogoutSuccessHandler;
import com.example.libraryProject.Security.provider.CustomAuthenticationProvider;
import com.example.libraryProject.Security.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
    @Bean
    public AuthenticationProvider authenticationProvider() {
        return new CustomAuthenticationProvider();
    }
    @Bean
    public AuthenticationSuccessHandler successHandler(){
        return new CustomAuthenticationSuccessHandler();
    }
    @Bean
    public AuthenticationFailureHandler failureHandler() {
        return new CustomAuthenticationFailureHandler();
    }
    @Bean
    public LogoutSuccessHandler logoutSuccessHandler() {
        return new CustomLogoutSuccessHandler();
    }
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){
        return (web) -> web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }


//    https://velog.io/@woosim34/Spring-Security-6.1.0%EC%97%90%EC%84%9C-is-deprecated-and-marked-for-removal-%EC%98%A4%EB%A5%98
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(authorize -> authorize
                    .requestMatchers("/**")
                    .permitAll()
                    .requestMatchers("/member/**").hasRole("MANAGER")
                    .anyRequest().authenticated());

        httpSecurity.formLogin(formLogin -> formLogin.loginPage("/login")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/main")
                .successHandler(successHandler())
                .failureHandler(failureHandler()))
                .logout(logout -> logout.logoutSuccessHandler(logoutSuccessHandler()));


        return httpSecurity.build();
    }
}
