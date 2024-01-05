package com.example.libraryProject.Service.Impl;

import com.example.libraryProject.DTO.MemberDTO;
import com.example.libraryProject.Entity.Member;
import com.example.libraryProject.Exception.ExistMemberException;
import com.example.libraryProject.Repository.MemberRepository;
import com.example.libraryProject.Service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    @Override
    public void join(Member member) throws ExistMemberException{
        if (validToNotDuplicatedMember(member)) {
            throw new ExistMemberException("이미 존재하는 회원입니다.");
        }
        memberRepository.save(member);
    }

    @Override
    public List<Member> findAllMembers() {
        return memberRepository.findAll();
    }

    @Override
    public Member findMemberById(Long id) {
        return memberRepository.findById(id).orElse(null);
    }

    @Override
    public Member findMemberByCode(String code) {
        return memberRepository.findByCode(code).orElse(null);

    }

    @Override
    public Member findMemberByUsernameAndTel(String username, String tel) {
        return memberRepository.findByUsernameAndTel(username, tel).orElse(null);
    }

    private boolean validToNotDuplicatedMember(Member member) {
        return memberRepository.findByUsernameAndTel(member.getUsername(), member.getTel()).isPresent();
    }

    private MemberDTO buildMember(Member targetMember) {
        return MemberDTO.builder()
                .username(targetMember.getUsername())
                .tel(targetMember.getTel())
                .code(targetMember.getCode())
                .address(targetMember.getAddress())
                .build();
    }
}
