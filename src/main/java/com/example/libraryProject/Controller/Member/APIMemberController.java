package com.example.libraryProject.Controller.Member;

import com.example.libraryProject.Controller.Member.Form.ResponseMemberForm;
import com.example.libraryProject.Controller.Member.Form.SignUpMemberForm;
import com.example.libraryProject.DTO.MessageResponseDTO;
import com.example.libraryProject.Entity.Address;
import com.example.libraryProject.Entity.Member;
import com.example.libraryProject.Exception.ExistMemberException;
import com.example.libraryProject.Service.MemberService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/apiMember")
public class APIMemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<MessageResponseDTO> sighUp(@RequestBody SignUpMemberForm signUpMemberForm) throws ExistMemberException {
        if (memberService.validToNotDuplicatedMember(signUpMemberForm.getUsername(), signUpMemberForm.getTel())) {
            throw new ExistMemberException("이미 존재하는 회원입니다.");
        }

        String memberCode = UUID.randomUUID().toString();
        Address address = new Address(signUpMemberForm.getAddress_LoadName(), signUpMemberForm.getAddress_Detail());
        Member member = new Member(signUpMemberForm.getUsername(), signUpMemberForm.getPassword(),
                signUpMemberForm.getTel(), address, memberCode);

        memberService.join(member);

        MessageResponseDTO messageResponseDTO = new MessageResponseDTO("Success", HttpStatus.OK.value(),
                new ResponseMemberForm(signUpMemberForm.getUsername(), memberCode));

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));


        return new ResponseEntity<>(messageResponseDTO, httpHeaders, HttpStatus.OK);
    }

    @GetMapping("/members/{memberId}")
    public ResponseEntity<MessageResponseDTO> getMemberBy(@PathVariable String memberId) {
        return null;
    }
}
