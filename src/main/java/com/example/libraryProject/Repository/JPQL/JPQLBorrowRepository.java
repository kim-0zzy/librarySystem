package com.example.libraryProject.Repository.JPQL;

import com.example.libraryProject.DTO.SearchCondition;
import com.example.libraryProject.Entity.Borrow;
import com.example.libraryProject.Exception.NotFoundResultException;
import com.example.libraryProject.Repository.BorrowRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Not;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JPQLBorrowRepository implements BorrowRepository {

    private final EntityManager em;

    @Override
    public void save(Borrow borrow){
        em.persist(borrow);
    }

    @Override
    public Optional<Borrow> findById(Long id){
        Borrow borrow = em.find(Borrow.class, id);
        return Optional.ofNullable(borrow);
    }

    @Override
    public List<Borrow> findBorrowByBookCondition(SearchCondition cond) throws NotFoundResultException {
        return null;
    }

    @Override
    public List<Borrow> findBorrowByMemberCondition(SearchCondition cond) throws NotFoundResultException {
        return null;
    }

    public List<Borrow> findBorrowByMemberCode(String code) throws NotFoundResultException{
        try{
            return em.createQuery("select b, m.code, m.username, bk.code, bk.name, bk.price, bk.state" +
                            " from Borrow b" +
                            " join fetch b.member m" +
                            " join fetch b.book bk" +
                            " where m.code =: code ", Borrow.class)
                    .setParameter("code", code)
                    .getResultList();
        }catch (Exception e){
            return null;
        }
    }

    public List<Borrow> findBorrowByUsernameWithPassword(String name, String password) throws NotFoundResultException{
        try{
            return em.createQuery("select b, m.code, m.username, bk.code, bk.name, bk.price, bk.state" +
                            " from Borrow b" +
                            " join fetch b.member m" +
                            " join fetch b.book bk" +
                            " where m.username =: username and m.password =: password ", Borrow.class)
                    .setParameter("username", name)
                    .setParameter("password", password)
                    .getResultList();
        }catch (Exception e){
            return null;
        }
    }

    public List<Borrow> findBorrowByBookCode(String code) throws NotFoundResultException{
        try{
            return em.createQuery("select b, m.code, m.username, bk.code, bk.name, bk.price, bk.state" +
                    " from Borrow b" +
                    " join fetch b.member m" +
                    " join fetch b.book bk" +
                    " where bk.code =: code", Borrow.class)
                    .setParameter("code", code)
                    .getResultList();
        }catch (Exception e){
            return null;
        }
    }

    public List<Borrow> findBorrowByBookName(String name) throws NotFoundResultException{
        try{
            return em.createQuery("select b, m.code, m.username, bk.code, bk.name, bk.price, bk.state" +
                            " from Borrow b" +
                            " join fetch b.member m" +
                            " join fetch b.book bk" +
                            " where bk.name =: name", Borrow.class)
                    .setParameter("name", name)
                    .getResultList();
        }catch (Exception e){
            return null;
        }
    }
}