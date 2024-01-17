package com.example.libraryProject.Controller.Book;

import com.example.libraryProject.Config.MemberAndBookHolder;
import com.example.libraryProject.Controller.Book.Form.RegisterBookForm;
import com.example.libraryProject.Controller.Member.Form.ResponseMemberForm;
import com.example.libraryProject.Controller.Member.Form.SignUpMemberForm;
import com.example.libraryProject.DTO.BookDTO;
import com.example.libraryProject.DTO.MessageResponseDTO;
import com.example.libraryProject.DTO.SearchCondition;
import com.example.libraryProject.Entity.Address;
import com.example.libraryProject.Entity.Book;
import com.example.libraryProject.Entity.Member;
import com.example.libraryProject.Exception.ExistMemberException;
import com.example.libraryProject.Exception.NotExsistConditionException;
import com.example.libraryProject.Service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/apiBook")
public class APIBookController {

    private final BookService bookService;
    @Autowired
    private MemberAndBookHolder memberAndBookHolder;

    @PostMapping("/register")
    public ResponseEntity<MessageResponseDTO> registerBook(@RequestBody RegisterBookForm registerBookForm) {
        String bookCode = "book_" + UUID.randomUUID();
        Book book = new Book(registerBookForm.getName(), bookCode, registerBookForm.getPlace(), registerBookForm.getPrice());
        bookService.join(book);

        MessageResponseDTO messageResponseDTO = new MessageResponseDTO("Register Success", HttpStatus.CREATED.value(),
                bookService.buildBookDTO(book));

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));


        return new ResponseEntity<>(messageResponseDTO, httpHeaders, HttpStatus.OK);
    }

    @GetMapping("/books/allBooks")
    public ResponseEntity<MessageResponseDTO> AllBook() {
        MessageResponseDTO messageResponseDTO = new MessageResponseDTO("Search Success", HttpStatus.OK.value(),
                bookService.findAllBooks());

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        return new ResponseEntity<>(messageResponseDTO, httpHeaders, HttpStatus.OK);
    }

    @GetMapping("/books/searchOne")
    public ResponseEntity<MessageResponseDTO> searchBook(@RequestBody SearchCondition searchCondition) throws NotExsistConditionException {
        if (!(StringUtils.hasText(searchCondition.getBookCode()) || (StringUtils.hasText(searchCondition.getBookName())))) {
            throw new NotExsistConditionException("Not Exist In Search Condition");
        }

        MessageResponseDTO messageResponseDTO = new MessageResponseDTO("Search Success", HttpStatus.OK.value());

        if (StringUtils.hasText(searchCondition.getBookName())) {
            List<BookDTO> bookDTOList = bookService.findBookByTitle(searchCondition.getBookName())
                    .stream()
                    .map(bookService::buildBookDTO)
                    .collect(Collectors.toList());
            messageResponseDTO.setObject(bookDTOList);
        }
        if (StringUtils.hasText(searchCondition.getBookCode())) {
            BookDTO bookDTO = bookService.buildBookDTO(bookService.findBookByCode(searchCondition.getBookCode()));
            messageResponseDTO.setObject(bookDTO);
        }
//        if (memberAndBookHolder.getQueriedMember().size() > 0) {
//
//            memberAndBookHolder.getQueriedMember().put("selectedMember", b);
//        }

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        return new ResponseEntity<>(messageResponseDTO, httpHeaders, HttpStatus.OK);
    }

    @Transactional
    @PostMapping("/books/stateChange")
    public ResponseEntity<MessageResponseDTO> stateChange(@RequestBody SearchCondition searchCondition) throws NotExsistConditionException {
        if (!(StringUtils.hasText(searchCondition.getBookCode()))) {
            throw new NotExsistConditionException("Not Exist In Search Condition");
        }

        Book book = bookService.findBookByCode(searchCondition.getBookCode());
        book.toggleState();
        BookDTO bookDTO = bookService.buildBookDTO(book);

        MessageResponseDTO messageResponseDTO = new MessageResponseDTO("Update Success", HttpStatus.OK.value(), bookDTO);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        return new ResponseEntity<>(messageResponseDTO, httpHeaders, HttpStatus.OK);
    }
}
