package com.GestionDeStock.Repository;

import com.GestionDeStock.Entity.Facture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FactureRepository extends JpaRepository<Facture , Integer> {

    @Query("SELECT c.facture FROM Commande c WHERE c.idcommande = :commandId")
    Facture findFactureByCommandIdJPQL(int commandId);
}
