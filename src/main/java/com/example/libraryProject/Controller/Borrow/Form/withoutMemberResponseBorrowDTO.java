package com.example.libraryProject.Controller.Borrow.Form;

import lombok.Data;

@Data
public class withoutMemberResponseBorrowDTO {
    private String bookName;
    private String bookCode;

    public withoutMemberResponseBorrowDTO(String bookName, String bookCode) {
        this.bookName = bookName;
        this.bookCode = bookCode;
    }
}
