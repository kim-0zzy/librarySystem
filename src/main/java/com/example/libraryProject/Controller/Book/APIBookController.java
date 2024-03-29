package com.example.libraryProject.Controller.Book;

import com.example.libraryProject.Config.MemberAndBookHolder;
import com.example.libraryProject.Controller.Book.Form.RegisterBookForm;
import com.example.libraryProject.DTO.BookDTO;
import com.example.libraryProject.DTO.MessageResponseDTO;
import com.example.libraryProject.DTO.SearchCondition;
import com.example.libraryProject.Entity.Book;
import com.example.libraryProject.Exception.NotExistConditionException;
import com.example.libraryProject.Exception.NotFoundResultException;
import com.example.libraryProject.Exception.ResultListIsEmptyException;
import com.example.libraryProject.Service.BookService;
import lombok.RequiredArgsConstructor;
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
    private final MemberAndBookHolder memberAndBookHolder;

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
    public ResponseEntity<MessageResponseDTO> AllBook() throws ResultListIsEmptyException {

        List<Book> allBooks = bookService.findAllBooks();
        if (allBooks.isEmpty()) {
            throw new ResultListIsEmptyException("List is Empty");
        }

        List<BookDTO> bookDTOList = new ArrayList<>();
        for (Book book : allBooks) {
            bookDTOList.add(bookService.buildBookDTO(book));
        }

        MessageResponseDTO messageResponseDTO = new MessageResponseDTO("Search Success", HttpStatus.OK.value(),
                bookDTOList);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        return new ResponseEntity<>(messageResponseDTO, httpHeaders, HttpStatus.OK);
    }

    @GetMapping("/books/searchOne")
    public ResponseEntity<MessageResponseDTO> searchBook(@RequestBody SearchCondition searchCondition)
            throws NotExistConditionException, ResultListIsEmptyException, NotFoundResultException {
        if (!(StringUtils.hasText(searchCondition.getBookCode()) || (StringUtils.hasText(searchCondition.getBookName())))) {
            throw new NotExistConditionException("Not Exist In Search Condition");
        }

        if (memberAndBookHolder.getQueriedMember().size() > 0) {
            memberAndBookHolder.getQueriedBook().clear();
        }

        MessageResponseDTO messageResponseDTO = new MessageResponseDTO("Search Success", HttpStatus.OK.value());

        if (StringUtils.hasText(searchCondition.getBookName())) {
            List<Book> bookList = bookService.findBookByTitle(searchCondition.getBookName());

            if (bookList.isEmpty()) {
                throw new ResultListIsEmptyException("List is Empty");
            }

            int count = 1;
            for (Book book : bookList) {
                memberAndBookHolder.getQueriedBook().put(
                        memberAndBookHolder.getQueriedMember()
                                .get("selectedMember").toString()+"_" + count
                        , book);

                count = count++;
            }
            List<BookDTO> bookDTOList = bookList
                    .stream()
                    .map(bookService::buildBookDTO)
                    .collect(Collectors.toList());
            messageResponseDTO.setObject(bookDTOList);
        }
        if (StringUtils.hasText(searchCondition.getBookCode())) {
            Book book = bookService.findBookByCode(searchCondition.getBookCode());

            if (book == null) {
                throw new NotFoundResultException("Result is Not Found");
            }

            memberAndBookHolder.getQueriedBook().put(
                    memberAndBookHolder.getQueriedMember()
                            .get("selectedMember").toString()
                    , book);

            BookDTO bookDTO = bookService.buildBookDTO(book);
            messageResponseDTO.setObject(bookDTO);
        }

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        return new ResponseEntity<>(messageResponseDTO, httpHeaders, HttpStatus.OK);
    }

    @Transactional
    @PostMapping("/books/stateChange")
    public ResponseEntity<MessageResponseDTO> stateChange(@RequestBody SearchCondition searchCondition)
            throws NotExistConditionException, NotFoundResultException {
        if (!(StringUtils.hasText(searchCondition.getBookCode()))) {
            throw new NotExistConditionException("Not Exist In Search Condition");
        }

        Book book = bookService.findBookByCode(searchCondition.getBookCode());

        if (book == null) {
            throw new NotFoundResultException("Result is Not Found");
        }

        book.toggleState();
        BookDTO bookDTO = bookService.buildBookDTO(book);

        MessageResponseDTO messageResponseDTO = new MessageResponseDTO("Update Success", HttpStatus.OK.value(), bookDTO);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        return new ResponseEntity<>(messageResponseDTO, httpHeaders, HttpStatus.OK);
    }
}
