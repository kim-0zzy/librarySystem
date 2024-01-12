package com.example.libraryProject.Repository.QueryDSL;

import com.example.libraryProject.Entity.Book;
import com.example.libraryProject.Entity.QBook;
import com.example.libraryProject.Repository.BookRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.example.libraryProject.Entity.QBook.book;

@Repository
@RequiredArgsConstructor
@Primary
public class DSLBookRepository implements BookRepository {

    private final EntityManager em;
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public void save(Book book) {
        em.persist(book);
    }

    @Override
    public Optional<Book> findById(Long id){
        Book book = em.find(Book.class, id);
        return Optional.ofNullable(book);
    }
    @Override
    public List<Book> findAll() {
        return jpaQueryFactory
                .selectFrom(book)
                .fetch();
    }
    @Override
    public List<Book> findByBookName(String bookName){
        return jpaQueryFactory
                .selectFrom(book)
                .where(book.name.eq(bookName))
                .fetch();
    }
    @Override
    public Book findByCode(String code){
        return jpaQueryFactory
                .selectFrom(book)
                .where(book.code.eq(code))
                .fetchOne();
    }
}
