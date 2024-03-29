package com.example.libraryProject.Controller.Borrow;

import com.example.libraryProject.Config.MemberAndBookHolder;
import com.example.libraryProject.Controller.Borrow.Form.ResponseBorrowDTO;
import com.example.libraryProject.Controller.Borrow.Form.withoutMemberResponseBorrowDTO;
import com.example.libraryProject.DTO.MessageResponseDTO;
import com.example.libraryProject.DTO.SearchCondition;
import com.example.libraryProject.Entity.Book;
import com.example.libraryProject.Entity.Borrow;
import com.example.libraryProject.Entity.Member;
import com.example.libraryProject.Exception.NotExistConditionException;
import com.example.libraryProject.Exception.NotFoundResultException;
import com.example.libraryProject.Exception.NotScannedException;
import com.example.libraryProject.Service.BookService;
import com.example.libraryProject.Service.BorrowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/apiBorrow")
public class APIBorrowController {

    private final BookService bookService;
    private final BorrowService borrowService;
    private final MemberAndBookHolder memberAndBookHolder;

    @PostMapping("/rental")
    public ResponseEntity<MessageResponseDTO> rental() throws NotScannedException {
        if (memberAndBookHolder.getQueriedMember().isEmpty()) {
            throw new NotScannedException("Member is not Scan. Please scan Member");
        }
        if (memberAndBookHolder.getQueriedBook().isEmpty()) {
            throw new NotScannedException("Books are not Scan. Please scan Books");
        }

        Member member = memberAndBookHolder.getQueriedMember().get("selectedMember");

        List<Book> bookList = new ArrayList<>();
        List<ResponseBorrowDTO> responseBorrowDTOList = new ArrayList<>();

        for(String key : memberAndBookHolder.getQueriedBook().keySet()) {
            bookList.add(memberAndBookHolder.getQueriedBook().get(key));
        }

        for (Book book : bookList) {
            Borrow borrow = borrowService.createBorrow(member, book, 14);
            borrowService.join(borrow);
            book.toggleState();

            responseBorrowDTOList.add(new ResponseBorrowDTO(member.getUsername(), book.getName(), book.getCode()));
        }

        MessageResponseDTO messageResponseDTO = new MessageResponseDTO("Rental Success", HttpStatus.CREATED.value(),
                responseBorrowDTOList);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        return new ResponseEntity<>(messageResponseDTO, httpHeaders, HttpStatus.OK);
    }

    @PostMapping("/giveBack")
    public ResponseEntity<MessageResponseDTO> giveBack(@RequestBody SearchCondition searchCondition)
            throws NotExistConditionException, NotFoundResultException {

        if (searchCondition.getBookCode().isEmpty()) {
            throw new NotExistConditionException("Search Condition is Empty. Please Input BookCode");
        }

        Book book = bookService.findBookByCode(searchCondition.getBookCode());

        if (book == null) {
            throw new NotFoundResultException("Result is Not Found. Please Retry other Code");
        }

        borrowService.deleteBorrow(searchCondition);
        book.toggleState();

        MessageResponseDTO messageResponseDTO = new MessageResponseDTO("Return Success", HttpStatus.CREATED.value(),
                new withoutMemberResponseBorrowDTO(book.getName(), book.getCode(), book.isState()));

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        return new ResponseEntity<>(messageResponseDTO, httpHeaders, HttpStatus.OK);
    }
}
