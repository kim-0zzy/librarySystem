package com.example.libraryProject.Service;

import com.example.libraryProject.DTO.ReturnBorrowDTO;
import com.example.libraryProject.DTO.SearchCondition;
import com.example.libraryProject.Entity.Borrow;

import java.util.List;
import java.util.Optional;

public interface BorrowService {

    void join(Borrow borrow);
    Optional<Borrow> findBorrowById(Long id);
    List<Borrow> findAllBorrows();
    List<ReturnBorrowDTO> findBorrowByCondition(SearchCondition cond);
    //List<ReturnBorrowDTO> findBorrowByBook(SearchCondition condition);
}
