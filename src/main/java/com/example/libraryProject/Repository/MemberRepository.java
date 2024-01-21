package com.example.libraryProject.Repository;

import com.example.libraryProject.Entity.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    void save(Member member);
    Optional<Member> findById(Long id);
    List<Member> findAll();
    Optional<Member> findByCode(String code);
    Optional<Member> findByUsernameAndTel(String username, String tel);
}
