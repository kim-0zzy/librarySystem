package com.example.libraryProject.Repository;

import com.example.libraryProject.DTO.ReturnBorrowDTO;
import com.example.libraryProject.DTO.SearchCondition;
import com.example.libraryProject.Entity.Borrow;
import com.example.libraryProject.Exception.NotFoundResultException;

import java.util.List;
import java.util.Optional;

public interface BorrowRepository {

    void save(Borrow borrow);
    Optional<Borrow> findById(Long id);
    List<ReturnBorrowDTO> findAll();
    List<ReturnBorrowDTO> findBorrowByBookCondition(SearchCondition cond);
    List<ReturnBorrowDTO> findBorrowByMemberCondition(SearchCondition cond);

}
