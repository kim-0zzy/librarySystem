package com.example.libraryProject.Repository;

import com.example.libraryProject.Entity.BorrowHistory;

import java.util.List;

public interface BorrowHistoryRepository {
    void save(BorrowHistory borrowHistory);
    List<BorrowHistory> findAll();
    void delete(String year, String month);
    List<BorrowHistory> findAllByMemberCode(String memberCode);
    List<BorrowHistory> findAllByBookCode(String bookCode);
    List<BorrowHistory> findAllByPeriod(String year, String month, String  day);
}
