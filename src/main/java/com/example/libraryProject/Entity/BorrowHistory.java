package com.example.libraryProject.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BorrowHistory {

    @Id @GeneratedValue
    @Column(name = "borrowHistory_id")
    private Long id;
    private String borrowedMemberCode;
    private String borrowedBookCode;
    private String borrowedBookName;
    private LocalDate borrowedDate;
    private LocalDate returnedDate;

    public BorrowHistory(String borrowedMemberCode, String borrowedBookCode, String borrowedBookName, LocalDate borrowedDate, LocalDate returnedDate) {
        this.borrowedMemberCode = borrowedMemberCode;
        this.borrowedBookCode = borrowedBookCode;
        this.borrowedBookName = borrowedBookName;
        this.borrowedDate = borrowedDate;
        this.returnedDate = returnedDate;
    }

}
