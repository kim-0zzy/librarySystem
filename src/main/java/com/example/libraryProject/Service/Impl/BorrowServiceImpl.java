package com.example.libraryProject.Service.Impl;

import com.example.libraryProject.DTO.BorrowDTO;
import com.example.libraryProject.DTO.ReturnBorrowDTO;
import com.example.libraryProject.DTO.SearchCondition;
import com.example.libraryProject.Entity.Book;
import com.example.libraryProject.Entity.Borrow;
import com.example.libraryProject.Entity.BorrowHistory;
import com.example.libraryProject.Entity.Member;
import com.example.libraryProject.Repository.BookRepository;
import com.example.libraryProject.Repository.BorrowHistoryRepository;
import com.example.libraryProject.Repository.BorrowRepository;
import com.example.libraryProject.Service.BorrowService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class BorrowServiceImpl implements BorrowService {

    private final BorrowRepository borrowRepository;
    private final BorrowHistoryRepository borrowHistoryRepository;
    @Override
    public void join(Borrow borrow) {
        borrowRepository.save(borrow);
    }

    @Override
    public BorrowDTO findBorrowById(Long id) {
        Optional<Borrow> borrow = borrowRepository.findById(id);
        return borrow.map(this::buildBorrowDTO).orElse(null);
    }

    @Override
    public List<ReturnBorrowDTO> findAllBorrows() {
        return borrowRepository.findAll();
    }

    @Override
    public List<ReturnBorrowDTO> findBorrowByCondition(SearchCondition cond) {
        if(!StringUtils.hasText(cond.getBookName()) || !StringUtils.hasText(cond.getBookCode())){
            return borrowRepository.findBorrowByMemberCondition(cond);
        }
        if ((!StringUtils.hasText(cond.getMemberName()) && !StringUtils.hasText(cond.getMemberTel()))
                || !StringUtils.hasText(cond.getMemberCode())){
            return borrowRepository.findBorrowByMemberCondition(cond);
        }
        return new ArrayList<>();
    }

    @Override
    public Borrow createBorrow(Member member, Book book, int limitDay){
        Borrow borrow = new Borrow(limitDay);
        borrow.setMember(member);
        borrow.setBook(book);
        return borrow;
    }

    @Override
    public void deleteBorrow(SearchCondition cond) {
        Optional<ReturnBorrowDTO> returnBorrowDTO = borrowRepository.findBorrowByBookCondition(cond).stream().findFirst();
        returnBorrowDTO.ifPresent(dto -> {
            BorrowHistory borrowHistory = new BorrowHistory(dto.getMemberCode(), dto.getBookCode(), dto.getBookName(), dto.getBorrowDate(), LocalDate.now());
            borrowHistoryRepository.save(borrowHistory);
        });
        borrowRepository.deleteByBookCode(cond.getBookCode());

    }

    private BorrowDTO buildBorrowDTO(Borrow borrow) {
        return BorrowDTO.builder()
                .borrowDate(borrow.getBorrowDate())
                .limitDate(borrow.getLimitDate())
                .build();
    }

}
