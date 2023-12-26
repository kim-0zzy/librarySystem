package com.example.libraryProject.Repository;

import com.example.libraryProject.Entity.Borrow;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class JPQLBorrowRepository {

    private final EntityManager em;

    public void save(Borrow borrow){
        em.persist(borrow);
    }

    public Borrow findById(String id){
        return em.find(Borrow.class, id);
    }

    public List<Borrow> findBorrowByMemberCode(String code) throws Exception{
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

    public List<Borrow> findBorrowByUsernameWithPassword(String name, String password) throws Exception{
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

    public List<Borrow> findBorrowByBookCode(String code) throws Exception{
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

    public List<Borrow> findBorrowByBookName(String name) throws Exception{
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
