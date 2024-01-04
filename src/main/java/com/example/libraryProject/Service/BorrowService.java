package com.example.libraryProject.Service;

import com.example.libraryProject.DTO.BorrowDTO;
import com.example.libraryProject.DTO.ReturnBorrowDTO;
import com.example.libraryProject.DTO.SearchCondition;
import com.example.libraryProject.Entity.Book;
import com.example.libraryProject.Entity.Borrow;
import com.example.libraryProject.Entity.Member;

import java.util.List;
import java.util.Optional;

public interface BorrowService {
    void join(Borrow borrow);
    BorrowDTO findBorrowById(Long id);
    List<ReturnBorrowDTO> findAllBorrows();
    List<ReturnBorrowDTO> findBorrowByCondition(SearchCondition cond);
    Borrow createBorrow(Member member, Book book, int limitDay);
    void deleteBorrow(SearchCondition cond);
}
