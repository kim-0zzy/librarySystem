package com.example.libraryProject.Repository.QueryDSL;

import com.example.libraryProject.Entity.Manager;
import com.example.libraryProject.Entity.QManager;
import com.example.libraryProject.Repository.ManagerRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class DSLManagerRepository implements ManagerRepository {
    private final EntityManager em;
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public void save(Manager manager) {
        em.persist(manager);
    }

    @Override
    public Optional<Manager> findById(Long id) {
        Manager manager = em.find(Manager.class, id);
        return Optional.ofNullable(manager);
    }

    @Override
    public List<Manager> findAll() {
        return jpaQueryFactory
                .selectFrom(QManager.manager)
                .fetch();
    }

    @Override
    public Optional<Manager> findByName(String username) {
        Manager manager = jpaQueryFactory
                .selectFrom(QManager.manager)
                .where(QManager.manager.managerName.eq(username))
                .fetchOne();

        return Optional.ofNullable(manager);
    }
}
