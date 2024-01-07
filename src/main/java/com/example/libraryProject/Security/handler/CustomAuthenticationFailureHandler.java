package com.example.libraryProject.Security.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String errorMessage = "Please Check Your ID&PW Again ";

        if (exception instanceof BadCredentialsException) {
            errorMessage = "Please Check Your Password Again ";
        } else if (exception instanceof DisabledException) {
            errorMessage = "Account is Locked. Please contact Manager";
        } else if (exception instanceof UsernameNotFoundException) {
            errorMessage = "Account is not exist. Please Signup";
        }

        request.setAttribute("errorMessage", errorMessage);
        request.getRequestDispatcher("로그인에러페이지").forward(request,response);
    }
}
