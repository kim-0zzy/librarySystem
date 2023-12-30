package com.example.libraryProject.Service;

import com.example.libraryProject.Entity.Member;

import java.util.List;
import java.util.Optional;

public interface MemberService {
    void join(Member member);
    List<Member> findAllMembers();
    Optional<Member> findMemberById(Long id);
    Optional<Member> findMemberByCode(String code);
    Optional<Member> findMemberByUsernameAndTel(String username, String Tel);
}
