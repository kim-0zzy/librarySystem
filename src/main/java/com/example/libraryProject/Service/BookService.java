package com.example.libraryProject.Service;

import com.example.libraryProject.Entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {

    void join(Book book);
    List<Book> findAllBooks();
    Optional<Book> findBookById(Long id);
    Optional<Book> findBookByTitle(String bookName);
    Optional<Book> findBookByCode(String bookCode);

}
