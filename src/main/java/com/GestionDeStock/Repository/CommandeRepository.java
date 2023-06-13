package com.GestionDeStock.Repository;

import com.GestionDeStock.Entity.Commande;
import com.GestionDeStock.Entity.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommandeRepository extends JpaRepository<Commande , Integer> {

    List<Commande> findAllByTier_Type (Type type);

    @Query("SELECT c FROM Commande c WHERE c.tier.idtier = :tierId")
    List<Commande> findAllByTierId(@Param("tierId") int tierId);
}
