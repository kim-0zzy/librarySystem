package com.example.libraryProject.Security;

import com.example.libraryProject.Entity.Manager;
import com.example.libraryProject.Entity.Member;
import com.example.libraryProject.Exception.NotFoundResultException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class ManagerContext extends User {

    public ManagerContext(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }
}
