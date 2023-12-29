package com.example.libraryProject.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReturnBorrowDTO {

    private Long borrowId;
    private LocalDate borrowData;
    private LocalDate limitData;
    private String memberCode;
    private String memberUsername;
    private String bookCode;
    private String bookName;
    private Boolean bookState;

}
