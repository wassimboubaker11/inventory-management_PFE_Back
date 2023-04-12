package com.GestionDeStock.Repository;

import com.GestionDeStock.Entity.Gestionaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GestionaireRepository extends JpaRepository<Gestionaire, Integer> {
}
