package com.example.libraryProject.Service;

import com.example.libraryProject.DTO.ManagerDTO;
import com.example.libraryProject.DTO.MemberDTO;
import com.example.libraryProject.Entity.Manager;
import com.example.libraryProject.Entity.Member;

import java.util.List;

public interface ManagerService {

    void join_Manager(Manager manager);
    Manager findManagerById(Long id);
    Manager findManagerByName(String name);
    List<Manager> findAllManager();
    ManagerDTO buildManager(Manager targetManager);
}
