package com.example.libraryProject.Repository;

import com.example.libraryProject.Entity.Book;
import com.example.libraryProject.Exception.NotFoundResultException;

import java.util.List;
import java.util.Optional;

public interface BookRepository {

    void save(Book book);
    Optional<Book> findById(Long id);
    List<Book> findAll();
    List<Book> findByBookName(String bookName);
    List<Book> findByCode(String code);

}
