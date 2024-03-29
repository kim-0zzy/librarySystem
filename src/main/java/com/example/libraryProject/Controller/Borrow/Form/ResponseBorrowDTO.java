package com.example.libraryProject.Controller.Borrow.Form;

import lombok.Data;

@Data
public class ResponseBorrowDTO {
    private String memberName;
    private String bookName;
    private String bookCode;

    public ResponseBorrowDTO(String memberName, String bookName, String bookCode) {
        this.memberName = memberName;
        this.bookName = bookName;
        this.bookCode = bookCode;
    }
}
