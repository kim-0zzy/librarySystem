package com.example.libraryProject.Repository.QueryDSL;

import com.example.libraryProject.Entity.Member;
import com.example.libraryProject.Entity.QMember;
import com.example.libraryProject.Exception.NotFoundResultException;
import com.example.libraryProject.Repository.MemberRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
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
    public List<Member> findAll() throws NotFoundResultException {
        return jpaQueryFactory
                .selectFrom(QMember.member)
                .fetch();

    }

    @Override
    public Member findByCode(String code) throws NotFoundResultException {
        return jpaQueryFactory
                .selectFrom(QMember.member)
                .where(QMember.member.code.eq(code))
                .fetchOne();
    }

    @Override
    public Member findByUsernameAndTel(String username, String tel) throws NotFoundResultException {
        return jpaQueryFactory
                .selectFrom(QMember.member)
                .where(QMember.member.username.eq(username)
                .and(QMember.member.tel.eq(tel)))
                .fetchOne();
    }
}
