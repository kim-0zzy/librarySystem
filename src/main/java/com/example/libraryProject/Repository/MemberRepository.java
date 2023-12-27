package com.example.libraryProject.Repository;

import com.example.libraryProject.Entity.Member;
import com.example.libraryProject.Exception.NotFoundResultException;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    void save(Member member);
    Optional<Member> findById(Long id);
    List<Member> findAll() throws NotFoundResultException;
    Member findByCode(String code) throws NotFoundResultException;
    Member findByUsernameAndTel(String username, String tel) throws NotFoundResultException;
}
