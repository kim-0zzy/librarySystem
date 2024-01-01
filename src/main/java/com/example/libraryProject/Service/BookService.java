package com.example.libraryProject.Service;

import com.example.libraryProject.DTO.BookDTO;
import com.example.libraryProject.Entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {

    void join(Book book);
    List<BookDTO> findAllBooks();
    BookDTO findBookById(Long id);
    List<BookDTO> findBookByTitle(String bookName);
    List<BookDTO> findBookByCode(String bookCode);

}
