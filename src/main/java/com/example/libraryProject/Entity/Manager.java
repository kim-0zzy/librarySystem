package com.example.libraryProject.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Manager {

    @Id @GeneratedValue
    private Long id;
    private String managerName;
    private String realName;
    private String password;
    private String role;

    public Manager(String managerName, String realName, String password) {
        this.managerName = managerName;
        this.realName = realName;
        this.password = password;
        this.role = "ROLE_MANAGER";
    }
}
