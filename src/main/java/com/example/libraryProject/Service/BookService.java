package com.example.libraryProject.Service;

import com.example.libraryProject.DTO.BookDTO;
import com.example.libraryProject.Entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {

    void join(Book book);
    List<Book> findAllBooks();
    Book findBookById(Long id);
    List<Book> findBookByTitle(String bookName);
    List<Book> findBookByCode(String bookCode);

}
