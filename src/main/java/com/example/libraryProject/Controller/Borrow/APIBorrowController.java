package com.example.libraryProject.Controller.Borrow;

import com.example.libraryProject.Controller.Book.Form.RegisterBookForm;
import com.example.libraryProject.DTO.MessageResponseDTO;
import com.example.libraryProject.DTO.SearchCondition;
import com.example.libraryProject.Service.BookService;
import com.example.libraryProject.Service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/apuBorrow")
public class APIBorrowController {

    private final BookService bookService;
    private final MemberService memberService;

    @PostMapping("/register")
    public ResponseEntity<MessageResponseDTO> registerBorrow(@RequestBody SearchCondition searchCondition) {
        return null;
    }
}
