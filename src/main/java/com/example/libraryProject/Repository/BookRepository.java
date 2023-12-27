package com.example.libraryProject.Repository;

import com.example.libraryProject.Entity.Book;
import com.example.libraryProject.Exception.NotFoundResultException;

import java.util.List;
import java.util.Optional;

public interface BookRepository {

    void save(Book book);
    Optional<Book> findById(Long id) throws Exception;
    List<Book> findAll();
    List<Book> findByBookName(String bookName) throws Exception;
    List<Book> findByCode(String code) throws Exception;

}
