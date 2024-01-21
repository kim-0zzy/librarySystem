package com.example.libraryProject.Repository.QueryDSL;

import com.example.libraryProject.Entity.Member;
import com.example.libraryProject.Repository.MemberRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.example.libraryProject.Entity.QMember.member;

@Repository
@RequiredArgsConstructor
@Primary
public class DSLMemberRepository implements MemberRepository {
    private final EntityManager em;
    private final JPAQueryFactory jpaQueryFactory;


    @Override
    public void save(Member member) {
        em.persist(member);
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public List<Member> findAll(){
        return jpaQueryFactory
                .selectFrom(member)
                .fetch();

    }

    @Override
    public Optional<Member> findByCode(String code) {
        Member searchMember = jpaQueryFactory
                .selectFrom(member)
                .where(member.code.eq(code))
                .fetchOne();
        return Optional.ofNullable(searchMember);
    }

    @Override
    public Optional<Member> findByUsernameAndTel(String username, String tel){

        Member searchMember = jpaQueryFactory
                .selectFrom(member)
                .where(member.username.eq(username)
                .and(member.tel.eq(tel)))
                .fetchOne();
        return Optional.ofNullable(searchMember);
    }
}
