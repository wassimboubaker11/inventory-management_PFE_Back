package com.GestionDeStock.Repository;

import com.GestionDeStock.Entity.Depot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepotRepository extends JpaRepository<Depot , Integer> {
    boolean existsByNom(String nom);

}
