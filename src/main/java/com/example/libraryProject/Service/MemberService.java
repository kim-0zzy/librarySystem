package com.example.libraryProject.Service;

import com.example.libraryProject.DTO.MemberDTO;
import com.example.libraryProject.Entity.Member;
import com.example.libraryProject.Exception.ExistMemberException;

import java.util.List;
import java.util.Optional;

public interface MemberService {
    void join(Member member) throws ExistMemberException;
    List<MemberDTO> findAllMembers();
    MemberDTO findMemberById(Long id);
    MemberDTO findMemberByCode(String code);
    MemberDTO findMemberByUsernameAndTel(String username, String Tel);
}
