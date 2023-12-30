package com.example.libraryProject.Repository.QueryDSL;

import com.example.libraryProject.DTO.ReturnBorrowDTO;
import com.example.libraryProject.DTO.SearchCondition;
import com.example.libraryProject.Entity.Borrow;
import com.example.libraryProject.Entity.QBook;
import com.example.libraryProject.Entity.QBorrow;
import com.example.libraryProject.Entity.QMember;
import com.example.libraryProject.Exception.NotFoundResultException;
import com.example.libraryProject.Repository.BorrowRepository;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

import static com.example.libraryProject.Entity.QBook.book;
import static com.example.libraryProject.Entity.QBorrow.borrow;
import static com.example.libraryProject.Entity.QMember.member;

@Repository
@RequiredArgsConstructor
@Primary
public class DSLBorrowRepository implements BorrowRepository {

    private final EntityManager em;
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public void save(Borrow borrow) {
        em.persist(borrow);
    }

    @Override
    public Optional<Borrow> findById(Long id) throws NotFoundResultException {
        Borrow borrow = em.find(Borrow.class, id);
        return Optional.ofNullable(borrow);
    }

    @Override
    public List<Borrow> findAll() {
        return jpaQueryFactory.selectFrom(borrow).fetch();
    }

    @Override
    public List<ReturnBorrowDTO> findBorrowByBookCondition(SearchCondition cond) throws NotFoundResultException {
        return jpaQueryFactory
                .select(Projections.constructor(ReturnBorrowDTO.class,
                                borrow.id, borrow.borrowDate, borrow.limitDate,
                                member.code, member.username,
                                book.code, book.name, book.state        )
                        )
                .from(borrow)
                .where(
                        bookCodeEq(cond.getBookCode()),
                        bookNameEq(cond.getBookName()))
                .join(borrow.book, book)
                .join(borrow.member, member)
                .fetch();
    }

    @Override
    public List<ReturnBorrowDTO> findBorrowByMemberCondition(SearchCondition cond) throws NotFoundResultException {
        return jpaQueryFactory
                .select(Projections.constructor(ReturnBorrowDTO.class,
                        borrow.id, borrow.borrowDate, borrow.limitDate,
                        member.code, member.username,
                        book.code, book.name, book.state        )
                )
                .from(borrow)
                .where(
                        memberCodeEq(cond.getMemberCode()),
                        memberNameAndTelEq(cond.getMemberName(), cond.getMemberTel()))
                .join(borrow.book, book)
                .join(borrow.member, member)
                .fetch();
    }

    private BooleanExpression bookNameEq(String bookName) {
        if (StringUtils.hasText(bookName)) {
            return book.name.eq(bookName);
        }
        return null;
    }

    private BooleanExpression bookCodeEq(String bookCode) {
        if (StringUtils.hasText(bookCode)) {
            return book.code.eq(bookCode);
        }
        return null;
    }

    private BooleanExpression memberNameAndTelEq(String memberName, String memberTel) {
        if (StringUtils.hasText(memberName) && StringUtils.hasText(memberTel)) {
            return member.username.eq(memberName).and(member.tel.eq(memberTel));
        }
        return null;
    }

    private BooleanExpression memberCodeEq(String memberCode) {
        if (StringUtils.hasText(memberCode)) {
            return member.username.eq(memberCode);
        }
        return null;
    }
}
