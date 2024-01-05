package com.example.libraryProject.Service.Impl;

import com.example.libraryProject.Entity.BorrowHistory;
import com.example.libraryProject.Repository.BorrowHistoryRepository;
import com.example.libraryProject.Service.BorrowHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BorrowHistoryServiceImpl implements BorrowHistoryService {
    private final BorrowHistoryRepository borrowHistoryRepository;
    @Override
    public List<BorrowHistory> loadAllHistory() {
        return borrowHistoryRepository.findAll();
    }

    @Override
    public List<BorrowHistory> loadAllHistoryByMemberCode(String memberCode) {
        return borrowHistoryRepository.findAllByMemberCode(memberCode);
    }

    @Override
    public List<BorrowHistory> loadAllHistoryByBookCode(String bookCode) {
        return borrowHistoryRepository.findAllByBookCode(bookCode);
    }

    @Override
    public List<BorrowHistory> loadAllHistoryByPeriodSet(String year, String month, String day) {
        return borrowHistoryRepository.findAllByPeriod(year, month, day);
    }
}
