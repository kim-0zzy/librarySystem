package com.example.libraryProject.Service.Impl;

import com.example.libraryProject.DTO.ManagerDTO;
import com.example.libraryProject.Entity.Manager;
import com.example.libraryProject.Repository.ManagerRepository;
import com.example.libraryProject.Service.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ManagerServiceImpl implements ManagerService{

    private final ManagerRepository managerRepository;

    @Override
    public void join_Manager(Manager manager) {
        managerRepository.save(manager);
    }

    @Override
    public Manager findManagerById(Long id) {
        Optional<Manager> manager = managerRepository.findById(id);
        return manager.orElse(null);
    }

    @Override
    public Manager findManagerByName(String name) {
        Optional<Manager> manager = managerRepository.findByName(name);
        return manager.orElse(null);
    }

    @Override
    public List<Manager> findAllManager() {
        return managerRepository.findAll();
    }

    @Override
    public ManagerDTO buildManager(Manager targetManager) {
        return ManagerDTO.builder()
                .managerName(targetManager.getManagerName())
                .role(targetManager.getRole())
                .build();
    }
}
