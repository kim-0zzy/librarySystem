package com.example.libraryProject.Service.Impl;

import com.example.libraryProject.DTO.BorrowDTO;
import com.example.libraryProject.DTO.ReturnBorrowDTO;
import com.example.libraryProject.DTO.SearchCondition;
import com.example.libraryProject.Entity.Book;
import com.example.libraryProject.Entity.Borrow;
import com.example.libraryProject.Entity.BorrowHistory;
import com.example.libraryProject.Entity.Member;
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
        BorrowHistory borrowHistory = new BorrowHistory(
                borrow.getMember().getCode(), borrow.getBook().getCode(),
                borrow.getBook().getName(), LocalDate.now(), "unReturned");
        borrowHistoryRepository.save(borrowHistory);
    }

    @Override
    public Borrow findBorrowById(Long id) {
        return borrowRepository.findById(id).orElse(null);
//        return borrow.map(this::buildBorrowDTO).orElse(null);
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
            List<BorrowHistory> borrowHistoryList = borrowHistoryRepository.findAllByBookCode(dto.getBookCode());
            borrowHistoryList.stream().findFirst().ifPresent(borrowHistory -> {
               borrowHistory.setReturnedDate(LocalDate.now().toString());
            });
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
