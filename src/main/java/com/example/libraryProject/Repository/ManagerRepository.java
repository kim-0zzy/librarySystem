package com.example.libraryProject.Repository;

import com.example.libraryProject.Entity.Manager;

import java.util.List;
import java.util.Optional;

public interface ManagerRepository {

    void save(Manager manager);
    Optional<Manager> findById(Long id);
    List<Manager> findAll();
    Optional<Manager> findByName(String username);
}
