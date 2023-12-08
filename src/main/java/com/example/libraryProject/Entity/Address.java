package com.example.libraryProject.Entity;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Address {

    private String roadName;
    private String detail;

    public Address(String roadName, String detail) {
        this.roadName = roadName;
        this.detail = detail;
    }
}
