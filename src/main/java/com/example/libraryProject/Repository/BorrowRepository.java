package com.example.libraryProject.Repository;

import com.example.libraryProject.DTO.ReturnBorrowDTO;
import com.example.libraryProject.DTO.SearchCondition;
import com.example.libraryProject.Entity.Borrow;
import com.example.libraryProject.Exception.NotFoundResultException;

import java.util.List;
import java.util.Optional;

public interface BorrowRepository {

    void save(Borrow borrow);
    Optional<Borrow> findById(Long id) throws NotFoundResultException;

    List<Borrow> findAll();
    List<ReturnBorrowDTO> findBorrowByBookCondition(SearchCondition cond) throws NotFoundResultException;
    List<ReturnBorrowDTO> findBorrowByMemberCondition(SearchCondition cond) throws NotFoundResultException;

}
