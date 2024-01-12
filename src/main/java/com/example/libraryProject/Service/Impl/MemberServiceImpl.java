package com.example.libraryProject.Service.Impl;

import com.example.libraryProject.Controller.Member.Form.UpdateMemberForm;
import com.example.libraryProject.DTO.MemberDTO;
import com.example.libraryProject.Entity.Address;
import com.example.libraryProject.Entity.Member;
import com.example.libraryProject.Repository.MemberRepository;
import com.example.libraryProject.Service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    @Override
    public void join(Member member){
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

    @Override
    public Boolean validToNotDuplicatedMember(String username, String tel) {
        return memberRepository.findByUsernameAndTel(username, tel).isPresent();
    }

    @Override
    public MemberDTO buildMember(Member targetMember) {
        return MemberDTO.builder()
                .username(targetMember.getUsername())
                .tel(targetMember.getTel())
                .code(targetMember.getCode())
                .address(targetMember.getAddress())
                .build();
    }

    @Override
    public String updateMember(Member member, UpdateMemberForm updateMemberForm) {
        String updateHistory = "";
        if (StringUtils.hasText(updateMemberForm.getTel())) {
            updateHistory += member.getTel() + "→" + updateMemberForm.getTel() + "\n";
            member.setTel(updateMemberForm.getTel());
        }
        if (StringUtils.hasText(updateMemberForm.getAddress_LoadName()) && StringUtils.hasText(updateMemberForm.getAddress_Detail())) {
            updateHistory += member.getAddress().getRoadName() + "→" + updateMemberForm.getAddress_LoadName() + "\n" +
                    member.getAddress().getDetail() + "→" + updateMemberForm.getAddress_Detail();
            member.setAddress(new Address(updateMemberForm.getAddress_LoadName(), updateMemberForm.getAddress_Detail()));
        }
        return updateHistory;
    }
}
