package com.example.libraryProject.DTO;

import com.example.libraryProject.Entity.Book;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BorrowDTO {

//    borrow.id, borrow.borrowDate, borrow.limitDate,
//    member.code, member.username,
//    book.code, book.name, book.state

    private LocalDate borrowDate;
    private LocalDate limitDate;
    private String memberCode;
    private String username;
    private String bookCode;
    private String bookName;
    private Boolean bookState;
}
