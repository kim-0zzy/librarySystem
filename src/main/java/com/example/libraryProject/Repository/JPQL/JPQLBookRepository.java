package com.example.libraryProject.Repository.JPQL;

import com.example.libraryProject.Entity.Book;
import com.example.libraryProject.Exception.NotFoundResultException;
import com.example.libraryProject.Repository.BookRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class JPQLBookRepository implements BookRepository {

    private final EntityManager em;
    @Override
    public void save(Book book){
        em.persist(book);
    }
    @Override
    public Book findById(Long id) throws NotFoundResultException{
        return em.find(Book.class, id);
    }
    @Override
    public List<Book> findAll(){
        return em.createQuery("select b from Book b", Book.class)
                .getResultList();
    }
    @Override
    public Book findByCode(String code) throws NotFoundResultException {
        try{
            return em.createQuery("select b from Book b where b.code =: code"
                            , Book.class)
                    .setParameter("code", code)
                    .getSingleResult();
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List<Book> findByBookName(String name) throws NotFoundResultException{
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
