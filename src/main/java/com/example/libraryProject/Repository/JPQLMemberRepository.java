package com.example.libraryProject.Repository;

import com.example.libraryProject.Entity.Member;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class JPQLMemberRepository {

    private final EntityManager em;

    public void save(Member member){
        em.persist(member);
    }

    public Member findById(Long id){
        return em.find(Member.class, id);
    }
    public List<Member> findAll(){
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }
    public Member findByCode(String code) throws Exception{
        try{
            return em.createQuery("select m from Member m where m.code =: code"
                            , Member.class)
                    .setParameter("code", code)
                    .getSingleResult();
        }catch (Exception e){
            return null;
        }
    }
    public Member findByUsernameAndTel(String username, String tel) throws Exception {
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