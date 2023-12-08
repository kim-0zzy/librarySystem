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
    private Long id;
    private String name;
    private String code;
    private String place;
    private boolean state;
    private int price;

    public Book(String name, String code, String place, int price) {
        this.name = name;
        this.code = code;
        this.place = place;
        this.state = true;
        this.price = price;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "borrow")
    private List<Borrow> borrows = new ArrayList<>();

    public void setState(){
        this.state = !this.state;
    }
}
