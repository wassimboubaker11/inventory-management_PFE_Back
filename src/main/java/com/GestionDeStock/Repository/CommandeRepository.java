package com.GestionDeStock.Repository;

import com.GestionDeStock.Entity.Commande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommandeRepository extends JpaRepository<Commande , Integer> {
}
