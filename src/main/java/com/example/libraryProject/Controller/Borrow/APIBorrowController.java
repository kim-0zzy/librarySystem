package com.example.libraryProject.Controller.Borrow;

import com.example.libraryProject.Config.MemberAndBookHolder;
import com.example.libraryProject.Controller.Book.Form.RegisterBookForm;
import com.example.libraryProject.Controller.Borrow.Form.ResponseBorrowDTO;
import com.example.libraryProject.Controller.Member.Form.ResponseMemberForm;
import com.example.libraryProject.DTO.MessageResponseDTO;
import com.example.libraryProject.DTO.SearchCondition;
import com.example.libraryProject.Service.BookService;
import com.example.libraryProject.Service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;

@RestController
@RequiredArgsConstructor
@RequestMapping("/apiBorrow")
public class APIBorrowController {

    private final BookService bookService;
    private final MemberService memberService;

    @Autowired
    private MemberAndBookHolder memberAndBookHolder;

    @PostMapping("/rental")
    public ResponseEntity<MessageResponseDTO> rental(@RequestBody SearchCondition searchCondition) {


        MessageResponseDTO messageResponseDTO = new MessageResponseDTO("Success", HttpStatus.CREATED.value(),
                new ResponseBorrowDTO());

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));


        return new ResponseEntity<>(messageResponseDTO, httpHeaders, HttpStatus.OK);
    }

    @PostMapping("/giveBack")
    public ResponseEntity<MessageResponseDTO> giveBack(@RequestBody SearchCondition searchCondition) {


        MessageResponseDTO messageResponseDTO = new MessageResponseDTO("Success", HttpStatus.CREATED.value(),
                new ResponseBorrowDTO());

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));


        return new ResponseEntity<>(messageResponseDTO, httpHeaders, HttpStatus.OK);
    }
}
