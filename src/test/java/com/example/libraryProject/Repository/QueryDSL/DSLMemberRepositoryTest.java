package com.example.libraryProject.Repository.QueryDSL;

import com.example.libraryProject.DTO.BookDTO;
import com.example.libraryProject.DTO.MemberDTO;
import com.example.libraryProject.DTO.ReturnBorrowDTO;
import com.example.libraryProject.DTO.SearchCondition;
import com.example.libraryProject.Entity.Address;
import com.example.libraryProject.Entity.Book;
import com.example.libraryProject.Entity.Borrow;
import com.example.libraryProject.Entity.Member;
import com.example.libraryProject.Exception.ExistMemberException;
import com.example.libraryProject.Repository.MemberRepository;
import com.example.libraryProject.Service.BookService;
import com.example.libraryProject.Service.BorrowService;
import com.example.libraryProject.Service.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DSLMemberRepositoryTest {

    @Autowired
    private MemberService memberService;
    @Autowired
    private BookService bookService;
    @Autowired
    private BorrowService borrowService;

    @Test
    void save() throws ExistMemberException {

        Address address = new Address("사우중로 73번길, 39", "218동 704호");
        String code = UUID.randomUUID().toString();
        Member member = new Member("김영서", "1234", "01030709433", address, code);
        memberService.join(member);


        MemberDTO memberByCode = memberService.findMemberByCode(code);
        assertThat(member.getUsername()).isEqualTo(memberByCode.getUsername());

    }

    @Test
    void bookServiceTest() {
//        public Book(String name, String code, String place, int price) {
//            this.name = name;
//            this.code = code;
//            this.place = place;
//            this.state = true;
//            this.price = price;
//        }
        String code = UUID.randomUUID().toString();
        Book book = new Book("책1", code, "저기", 15000);
        bookService.join(book);
        List<BookDTO> bookDTOList = bookService.findBookByTitle("책1");
        Optional<BookDTO> bookDTO = bookDTOList.stream().findFirst();
        assertThat(book.getName()).isEqualTo(bookDTO.get().getName());
    }

    @Test
    void borrowServiceTest() throws ExistMemberException {
        String bookCode = UUID.randomUUID().toString();
        Book book = new Book("책1", bookCode, "저기", 15000);
        bookService.join(book);

        Address address = new Address("사우중로 73번길, 39", "218동 704호");
        String memberCode = UUID.randomUUID().toString();
        Member member = new Member("김영서", "1234", "01030709433", address, memberCode);
        memberService.join(member);

        Borrow borrow = new Borrow(14);
        Borrow saveBorrow = borrowService.createBorrow(member, book, borrow);
        borrowService.join(saveBorrow);

        SearchCondition searchCondition = new SearchCondition(book.getName(), book.getCode(), null, null, null);
        ReturnBorrowDTO first = borrowService.findBorrowByCondition(searchCondition).stream().findFirst().get();

        assertThat(saveBorrow.getBorrowDate()).isEqualTo(first.getBorrowData());



    }
}