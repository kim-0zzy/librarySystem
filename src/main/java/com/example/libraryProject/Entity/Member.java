package com.example.libraryProject.Entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @Embedded
    private Address address;
    private String username;
    private String password;
    private String tel;
    private String code;

    public Member(String username, String password, String tel, Address address, String code) {
        this.username = username;
        this.password = password;
        this.tel = tel;
        this.address = address;
        this.code = code;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "member")
    private List<Borrow> borrow = new ArrayList<>();
}

