package com.example.libraryProject.DTO;

import lombok.Data;

@Data
public class SearchCondition {

    private String bookName;
    private String bookCode;
    private String memberName;
    private String memberTel;
    private String memberCode;
}
