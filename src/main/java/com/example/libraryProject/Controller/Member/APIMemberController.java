package com.example.libraryProject.Controller.Member;

import com.example.libraryProject.Controller.Member.Form.ResponseMemberForm;
import com.example.libraryProject.Controller.Member.Form.SignUpMemberForm;
import com.example.libraryProject.Controller.Member.Form.UpdateMemberForm;
import com.example.libraryProject.DTO.MemberDTO;
import com.example.libraryProject.DTO.MessageResponseDTO;
import com.example.libraryProject.DTO.SearchCondition;
import com.example.libraryProject.Entity.Address;
import com.example.libraryProject.Entity.Member;
import com.example.libraryProject.Exception.ExistMemberException;
import com.example.libraryProject.Exception.NotExsistConditionException;
import com.example.libraryProject.Service.MemberService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.NullValueInNestedPathException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/apiMember")
public class APIMemberController {

    private final MemberService memberService;

    private Long loadLoginMember() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Member member = (Member) authentication.getPrincipal();
        return member.getId();
    }

    @PostMapping("/signup")
    public ResponseEntity<MessageResponseDTO> signUp(@RequestBody SignUpMemberForm signUpMemberForm) throws ExistMemberException {
        if (memberService.validToNotDuplicatedMember(signUpMemberForm.getUsername(), signUpMemberForm.getTel())) {
            throw new ExistMemberException("이미 존재하는 회원입니다.");
        }

        String memberCode = UUID.randomUUID().toString();
        Address address = new Address(signUpMemberForm.getAddress_LoadName(), signUpMemberForm.getAddress_Detail());
        Member member = new Member(signUpMemberForm.getUsername(), signUpMemberForm.getPassword(),
                signUpMemberForm.getTel(), address, memberCode);

        memberService.join(member);

        MessageResponseDTO messageResponseDTO = new MessageResponseDTO("Success", HttpStatus.CREATED.value(),
                new ResponseMemberForm(signUpMemberForm.getUsername(), memberCode));

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));


        return new ResponseEntity<>(messageResponseDTO, httpHeaders, HttpStatus.OK);
    }

    @GetMapping("/members")
    public ResponseEntity<MessageResponseDTO> AllMember() {

        MessageResponseDTO messageResponseDTO = new MessageResponseDTO("Success", HttpStatus.OK.value(),
                memberService.findAllMembers());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        return new ResponseEntity<>(messageResponseDTO, httpHeaders, HttpStatus.OK);
    }

    @GetMapping("/members/search")
    public ResponseEntity<MessageResponseDTO> searchMember(@RequestBody SearchCondition searchCondition) throws NotExsistConditionException {
        if (!(StringUtils.hasText(searchCondition.getMemberCode()) ||
                (StringUtils.hasText(searchCondition.getMemberName()) && StringUtils.hasText(searchCondition.getMemberTel())))) {
            throw new NotExsistConditionException("Not Exist In Search Condition ");
        }

        Member member = null;

        if (StringUtils.hasText(searchCondition.getMemberCode())) {
            member = memberService.findMemberByCode(searchCondition.getMemberCode());
        }
        if (StringUtils.hasText(searchCondition.getMemberName()) && StringUtils.hasText(searchCondition.getMemberTel())) {
            member = memberService.findMemberByUsernameAndTel(searchCondition.getMemberName(), searchCondition.getMemberTel());
        }

        MessageResponseDTO messageResponseDTO = new MessageResponseDTO("Success", HttpStatus.OK.value(),
                memberService.buildMember(member));

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        return new ResponseEntity<>(messageResponseDTO, httpHeaders, HttpStatus.OK);
    }

    @Transactional
    @PostMapping("/members/update")
    public ResponseEntity<MessageResponseDTO> updateMember(@RequestBody UpdateMemberForm updateMemberForm) {
        Member member = memberService.findMemberById(loadLoginMember());
        String updateHistory = memberService.updateMember(member, updateMemberForm);

        MessageResponseDTO messageResponseDTO = new MessageResponseDTO("Success", HttpStatus.OK.value(),
                updateHistory);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
        return new ResponseEntity<>(messageResponseDTO, httpHeaders, HttpStatus.OK);
    }
}
