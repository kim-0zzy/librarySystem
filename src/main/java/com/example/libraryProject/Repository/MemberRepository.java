package com.example.libraryProject.Repository;

import com.example.libraryProject.Entity.Member;
import com.example.libraryProject.Exception.NotFoundResultException;

import java.util.List;

public interface MemberRepository {
    void save(Member member);

    Member findById(Long id);
    List<Member> findAll() throws NotFoundResultException;
    Member findByCode(String code) throws NotFoundResultException;
    Member findByUsernameAndTel(String username, String tel) throws NotFoundResultException;
}
