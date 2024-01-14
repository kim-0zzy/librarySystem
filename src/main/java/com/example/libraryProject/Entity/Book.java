package com.example.libraryProject.Entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Book {

    @Id @GeneratedValue
    @Column(name = "book_id")
    private Long id;
    private String name;
    private String code;
    private String place;
    private int price;
    private boolean state;

    public Book(String name, String code, String place, int price) {
        this.name = name;
        this.code = code;
        this.place = place;
        this.state = true;
        this.price = price;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "book")
    private List<Borrow> borrow = new ArrayList<>();

    public void toggleState(){
        this.state = !this.state;
    }
}
