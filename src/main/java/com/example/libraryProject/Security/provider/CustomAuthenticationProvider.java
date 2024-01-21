package com.example.libraryProject.Security.provider;

import com.example.libraryProject.Security.ManagerContext;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

    public final UserDetailsService userDetailsService;

    public final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String managerName = authentication.getName();
        String password = (String) authentication.getCredentials();

        UserDetails userDetails = userDetailsService.loadUserByUsername(managerName);
        ManagerContext managerContext = new ManagerContext(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());

        if (!matchPassword(password, managerContext.getPassword())) {
            throw new BadCredentialsException(managerName + "'s password is not matched !");
        }
        if (!managerContext.isEnabled()) {
            throw new LockedException(managerName + "'s Account is Locked !");
        }
        return new UsernamePasswordAuthenticationToken(managerName, password, managerContext.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

    public boolean matchPassword(String tryPassword, String savedPassword){
        return passwordEncoder.matches(tryPassword, savedPassword);
    }
}
