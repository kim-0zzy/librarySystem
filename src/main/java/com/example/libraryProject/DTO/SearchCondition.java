package com.example.libraryProject.DTO;

import lombok.Data;

@Data
public class SearchCondition {
    public SearchCondition(String bookName, String bookCode, String memberName, String memberTel, String memberCode) {
        this.bookName = bookName;
        this.bookCode = bookCode;
        this.memberName = memberName;
        this.memberTel = memberTel;
        this.memberCode = memberCode;
    }

    private String bookName;
    private String bookCode;
    private String memberName;
    private String memberTel;
    private String memberCode;
}
