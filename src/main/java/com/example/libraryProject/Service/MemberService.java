package com.example.libraryProject.Service;

import com.example.libraryProject.DTO.MemberDTO;
import com.example.libraryProject.Entity.Member;
import com.example.libraryProject.Exception.ExistMemberException;

import java.util.List;
import java.util.Optional;

public interface MemberService {
    void join(Member member) throws ExistMemberException;
    List<Member> findAllMembers();
    Member findMemberById(Long id);
    Member findMemberByCode(String code);
    Member findMemberByUsernameAndTel(String username, String Tel);
}
