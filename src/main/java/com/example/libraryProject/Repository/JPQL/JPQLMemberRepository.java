package com.example.libraryProject.Repository.JPQL;

import com.example.libraryProject.Entity.Member;
import com.example.libraryProject.Exception.NotFoundResultException;
import com.example.libraryProject.Repository.MemberRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JPQLMemberRepository implements MemberRepository {

    private final EntityManager em;
    @Override
    public void save(Member member){
        em.persist(member);
    }
    @Override
    public Optional<Member> findById(Long id){
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }
    @Override
    public List<Member> findAll(){
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }
    @Override
    public Member findByCode(String code) throws NotFoundResultException {
        try{
            return em.createQuery("select m from Member m where m.code =: code"
                            , Member.class)
                    .setParameter("code", code)
                    .getSingleResult();
        }catch (Exception e){
            return null;
        }
    }
    @Override
    public Member findByUsernameAndTel(String username, String tel) throws NotFoundResultException {
        try{
            return em.createQuery("select m from Member m where m.username =: username and m.tel =: tel"
                            , Member.class)
                    .setParameter("username", username)
                    .setParameter("tel", tel)
                    .getSingleResult();
        }catch (Exception e){
            return null;
        }
    }
}