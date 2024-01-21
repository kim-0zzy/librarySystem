package com.example.libraryProject.Controller.Borrow.Form;

import lombok.Data;

@Data
public class withoutMemberResponseBorrowDTO {
    private String bookName;
    private String bookCode;
    private Boolean bookState;

    public withoutMemberResponseBorrowDTO(String bookName, String bookCode, Boolean bookState) {
        this.bookName = bookName;
        this.bookCode = bookCode;
        this.bookState = bookState;
    }
}
