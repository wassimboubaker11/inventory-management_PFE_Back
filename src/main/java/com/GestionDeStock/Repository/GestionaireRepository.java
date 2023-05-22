package com.GestionDeStock.Repository;

import com.GestionDeStock.Entity.Gestionaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GestionaireRepository extends JpaRepository<Gestionaire, Integer> {

    @Query("SELECT g FROM Gestionaire g WHERE g.admin.id = :adminId")
    List<Gestionaire> findAllByAdminId(@Param("adminId") Integer adminId);

    Gestionaire getGestionaireByEmail(String email);
}
