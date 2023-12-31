package com.example.libraryProject.Entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Borrow {
    @Id @GeneratedValue
    @Column(name = "borrow_id")
    private Long id;
    private LocalDate borrowDate;
    private LocalDate limitDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    public void setMember(Member member){
        this.member = member;
        member.getBorrow().add(this);
    }
    public void setBook(Book book){
        this.book = book;
        book.getBorrow().add(this);
    }

    public Borrow(int limitDays) {
        this.borrowDate = LocalDate.now();
        this.limitDate = LocalDate.now().plusDays(limitDays);
    }
}
