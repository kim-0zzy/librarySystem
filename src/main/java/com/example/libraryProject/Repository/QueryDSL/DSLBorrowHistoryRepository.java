package com.example.libraryProject.Repository.QueryDSL;

import com.example.libraryProject.Entity.BorrowHistory;
import com.example.libraryProject.Repository.BorrowHistoryRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.example.libraryProject.Entity.QBorrowHistory.*;

@Repository
@RequiredArgsConstructor
@Primary
public class DSLBorrowHistoryRepository implements BorrowHistoryRepository {

    private final EntityManager em;
    private final JPAQueryFactory jpaQueryFactory;
    @Override
    public void save(BorrowHistory borrowHistory) {
        em.persist(borrowHistory);
    }

    @Override
    public void delete(String  year, String  month) {
        String selectedDate = year + "-" + month + "-01";
        LocalDate date = LocalDate.parse(selectedDate, DateTimeFormatter.ISO_DATE);
        jpaQueryFactory.delete(borrowHistory)
                .where(borrowHistory.borrowedDate.loe(date))
                .execute();
        // 파싱 사용해서 서브쿼리로 불러오기. <--- 고려해보기
    }

    @Override
    public List<BorrowHistory> findAll() {
        return jpaQueryFactory.selectFrom(borrowHistory)
                .fetch();
    }

    @Override
    public List<BorrowHistory> findAllByMemberCode(String memberCode) {
        return jpaQueryFactory.selectFrom(borrowHistory)
                .where(borrowHistory.borrowedMemberCode.eq(memberCode))
                .orderBy(borrowHistory.borrowedDate.desc())
                .fetch();
    }

    @Override
    public List<BorrowHistory> findAllByBookCode(String bookCode) {
        return jpaQueryFactory.selectFrom(borrowHistory)
                .where(borrowHistory.borrowedBookCode.eq(bookCode))
                .orderBy(borrowHistory.borrowedDate.desc())
                .fetch();
    }

    @Override
    public List<BorrowHistory> findAllByPeriod(String year, String month, String  day) {
        String selectedDate = year + "-" + month + "-" + day;
        LocalDate date = LocalDate.parse(selectedDate, DateTimeFormatter.ISO_DATE);
        return jpaQueryFactory.selectFrom(borrowHistory)
                .where(borrowHistory.borrowedDate.loe(date))
                .orderBy(borrowHistory.borrowedDate.desc())
                .fetch();
        // 파싱 사용해서 서브쿼리로 불러오기. <-- 고려해보기
    }
}
