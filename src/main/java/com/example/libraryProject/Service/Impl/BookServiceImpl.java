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
    public List<BookDTO> findAllBooks() {
        List<Book> bookList = bookRepository.findAll();
        List<BookDTO> bookDTOList = new ArrayList<>();
        for (Book book : bookList) {
            bookDTOList.add(buildBookDTO(book));
        }
        return bookDTOList;
    }

    @Override
    public BookDTO findBookById(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        return book.map(this::buildBookDTO).orElse(null);
    }

    @Override
    public List<BookDTO> findBookByTitle(String bookName) {
        List<BookDTO> bookDTOList = new ArrayList<>();
        List<Book> bookList = bookRepository.findByBookName(bookName);
        for (Book book : bookList) {
            bookDTOList.add(buildBookDTO(book));
        }
        return bookDTOList;
    }

    @Override
    public List<BookDTO> findBookByCode(String bookCode) {
        List<BookDTO> bookDTOList = new ArrayList<>();
        List<Book> bookList = bookRepository.findByCode(bookCode);
        for (Book book : bookList) {
            bookDTOList.add(buildBookDTO(book));
        }
        return bookDTOList;
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
