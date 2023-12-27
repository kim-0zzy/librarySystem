package com.example.libraryProject.Repository;

import com.example.libraryProject.Entity.Book;
import com.example.libraryProject.Exception.NotFoundResultException;

import java.util.List;

public interface BookRepository {

    void save(Book book);
    List<Book> findAll();
    Book findById(Long id) throws NotFoundResultException;
    List<Book> findByBookName(String bookName) throws NotFoundResultException;
    Book findByCode(String code) throws NotFoundResultException;

}
