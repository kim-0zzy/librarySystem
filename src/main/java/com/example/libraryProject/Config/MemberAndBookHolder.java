package com.example.libraryProject.Config;

import com.example.libraryProject.Entity.Book;
import com.example.libraryProject.Entity.Member;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;


@Component
public class MemberAndBookHolder {

    private ConcurrentHashMap<String, Member> queriedMember = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String, Book> queriedBook = new ConcurrentHashMap<>();

    public ConcurrentHashMap<String, Member> getQueriedMember() {
        return queriedMember;
    }
    public ConcurrentHashMap<String, Book> getQueriedBook() {
        return queriedBook;
    }
}
