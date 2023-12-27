package com.example.libraryProject.Repository.QueryDSL;

import com.example.libraryProject.Entity.Book;
import com.example.libraryProject.Entity.QBook;
import com.example.libraryProject.Repository.BookRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class DSLBookRepository implements BookRepository {
    private final EntityManager em;
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public void save(Book book) {
        em.persist(book);
    }

    @Override
    public Optional<Book> findById(Long id) throws Exception {
        Book book = em.find(Book.class, id);
        return Optional.ofNullable(book);
    }
    @Override
    public List<Book> findAll() {
        return jpaQueryFactory
                .selectFrom(QBook.book)
                .fetch();
    }
    @Override
    public List<Book> findByBookName(String bookName) throws Exception {
        return jpaQueryFactory
                .selectFrom(QBook.book)
                .where(QBook.book.name.eq(bookName))
                .fetch();
    }
    @Override
    public List<Book> findByCode(String code) throws Exception {
        return jpaQueryFactory
                .selectFrom(QBook.book)
                .where(QBook.book.code.eq(code))
                .fetch();
    }
}
