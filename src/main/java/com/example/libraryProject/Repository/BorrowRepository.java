package com.example.libraryProject.Repository;

import com.example.libraryProject.Entity.Borrow;
import com.example.libraryProject.Exception.NotFoundResultException;

import java.util.List;

public interface BorrowRepository {

    void save(Borrow borrow);

    Borrow findById(Long id) throws NotFoundResultException;

    List<Borrow> findBorrowByBookCondition(String cond) throws NotFoundResultException;
    List<Borrow> findBorrowByMemberCondition(String cond) throws NotFoundResultException;

}
