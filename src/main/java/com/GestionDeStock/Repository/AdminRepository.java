package com.GestionDeStock.Repository;

import com.GestionDeStock.DTO.AdminDTO;
import com.GestionDeStock.Entity.Admin;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {

    Admin getAdminByEmail(String email);
}
