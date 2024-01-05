package com.example.libraryProject.Service;

import com.example.libraryProject.Entity.BorrowHistory;

import java.util.List;

public interface BorrowHistoryService {

    List<BorrowHistory> loadAllHistory();
    List<BorrowHistory> loadAllHistoryByMemberCode(String memberCode);
    List<BorrowHistory> loadAllHistoryByBookCode(String bookCode);
    List<BorrowHistory> loadAllHistoryByPeriodSet(String year, String month, String day);

}
