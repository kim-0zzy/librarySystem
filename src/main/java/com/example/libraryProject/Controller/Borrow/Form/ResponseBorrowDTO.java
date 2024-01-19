package com.example.libraryProject.Controller.Borrow.Form;

import lombok.Data;

@Data
public class ResponseBorrowDTO {
    private String memberName;
    private List<String> bookName;
}
