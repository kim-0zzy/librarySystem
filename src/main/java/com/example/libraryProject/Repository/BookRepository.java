package com.example.libraryProject.Repository;

import com.example.libraryProject.Entity.Book;
import com.example.libraryProject.Entity.Member;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BookRepository {

    private final EntityManager em;

    public void save(Book book){
        em.persist(book);
    }

    public Book findById(Long id){
        return em.find(Book.class, id);
    }
    public List<Book> findAll(){
        return em.createQuery("select b from Book b", Book.class)
                .getResultList();
    }
    public Book findByCode(String code) throws Exception{
        try{
            return em.createQuery("select b from Book b where b.code =: code"
                            , Book.class)
                    .setParameter("code", code)
                    .getSingleResult();
        }catch (Exception e){
            return null;
        }
    }

    public List<Book> findByBookName(String name) throws Exception{
        try{
            return em.createQuery("select b from Book b where b.name =: name"
            , Book.class)
                    .setParameter("name", name)
                    .getResultList();
        }catch (Exception e){
            return null;
        }
    }

    public List<Book> findByBookNameWithoutBorrowed(String name) throws Exception{
        try{
            return em.createQuery("select b from Book b where b.name =: name and b.state is true"
            , Book.class)
                    .setParameter("name", name)
                    .getResultList();
        }catch (Exception e){
            return null;
        }
    }
}
