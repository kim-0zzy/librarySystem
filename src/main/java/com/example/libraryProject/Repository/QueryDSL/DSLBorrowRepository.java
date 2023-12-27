package com.example.libraryProject.Repository.QueryDSL;

import com.example.libraryProject.DTO.SearchCondition;
import com.example.libraryProject.Entity.Borrow;
import com.example.libraryProject.Entity.QBook;
import com.example.libraryProject.Entity.QBorrow;
import com.example.libraryProject.Entity.QMember;
import com.example.libraryProject.Exception.NotFoundResultException;
import com.example.libraryProject.Repository.BorrowRepository;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
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
    public List<Borrow> findBorrowByBookCondition(SearchCondition cond) throws NotFoundResultException {
//        return jpaQueryFactory
//                .select(QBorrow.borrow.id, QBorrow.borrow.borrowDate, QBorrow.borrow.limitDate,
//                        QMember.member.code, QMember.member.username,
//                        QBook.book.code, QBook.book.name, QBook.book.state)
//                .from(QBorrow.borrow)
//                .where(
//                        bookCodeEq(cond.getBookCode()),
//                        bookNameEq(cond.getBookName()))
//                .join(QBorrow.borrow.book, QBook.book)
//                .join(QBorrow.borrow.member, QMember.member)
//                .fetch();
        return null;
    }

    @Override
    public List<Borrow> findBorrowByMemberCondition(SearchCondition cond) throws NotFoundResultException {
        return null;
    }


    private BooleanExpression bookNameEq(String bookName) {
        if (StringUtils.hasText(bookName)) {
            return QBook.book.name.eq(bookName);
        }
        return null;
    }

    private BooleanExpression bookCodeEq(String bookCode) {
        if (StringUtils.hasText(bookCode)) {
            return QBook.book.code.eq(bookCode);
        }
        return null;
    }

    private BooleanExpression memberNameAndTelEq(String memberName, String memberTel) {
        if (StringUtils.hasText(memberName) && StringUtils.hasText(memberTel)) {
            return QMember.member.username.eq(memberName).and(QMember.member.tel.eq(memberTel));
        }
        return null;
    }

    private BooleanExpression memberCodeEq(String memberCode) {
        if (StringUtils.hasText(memberCode)) {
            return QMember.member.username.eq(memberCode);
        }
        return null;
    }
}
