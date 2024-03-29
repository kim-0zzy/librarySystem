package com.example.libraryProject.Service;

import com.example.libraryProject.Controller.Member.Form.UpdateMemberForm;
import com.example.libraryProject.DTO.MemberDTO;
import com.example.libraryProject.Entity.Member;

import java.util.List;

public interface MemberService {
    void join(Member member);
    List<Member> findAllMembers();
    Member findMemberById(Long id);
    Member findMemberByCode(String code);
    Member findMemberByUsernameAndTel(String username, String tel);
    Boolean validToNotDuplicatedMember(String username, String tel);
    MemberDTO buildMember(Member targetMember);
    String updateMember(Member member, UpdateMemberForm updateMemberForm);
}
