package com.GestionDeStock.Repository;


import com.GestionDeStock.Entity.MVT;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MVTRepository extends JpaRepository<MVT , Integer> {
    @Query("SELECT m FROM MVT m WHERE m.commande.idcommande = :idCommande")
    List<MVT> findMVTByIdcommande(@Param("idCommande") int idCommande);
}
