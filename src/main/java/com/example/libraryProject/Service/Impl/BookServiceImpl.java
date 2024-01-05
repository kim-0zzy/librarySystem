package com.example.libraryProject.Service.Impl;

import com.example.libraryProject.DTO.BookDTO;
import com.example.libraryProject.Entity.Book;
import com.example.libraryProject.Repository.BookRepository;
import com.example.libraryProject.Service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    public void join(Book book) {
        // 전체 책 갯수 조회해서 책 수 +1로 번호 부여하기
        bookRepository.save(book);
    }

    @Override
    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book findBookById(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        return book.orElse(null);
    }

    @Override
    public List<Book> findBookByTitle(String bookName) {
        return bookRepository.findByBookName(bookName);
    }

    @Override
    public List<Book> findBookByCode(String bookCode) {
        return bookRepository.findByCode(bookCode);
    }

    private BookDTO buildBookDTO(Book book) {
        return BookDTO.builder()
                .name(book.getName())
                .code(book.getCode())
                .place(book.getPlace())
                .price(book.getPrice())
                .state(book.isState())
                .build();
    }
}
