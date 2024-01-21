package com.example.libraryProject.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReturnBorrowDTO {

    private Long borrowId;
    private LocalDate borrowDate;
    private LocalDate limitDate;
    private String memberCode;
    private String memberUsername;
    private String bookCode;
    private String bookName;
    private Boolean bookState;

}
