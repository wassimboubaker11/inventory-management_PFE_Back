package com.GestionDeStock.DTO;

import com.GestionDeStock.Entity.Commande;
import com.GestionDeStock.Entity.Facture;
import com.GestionDeStock.Entity.MVT;
import com.GestionDeStock.Entity.Tier;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommandeDTO {

    private int idcommande;

    private int quantite;

    private String nom;
    private Float montant;

    private LocalDateTime date;

    private TierDTO tier;

    private List<MVTDTO> mvtList;

    private FactureDTO factureDTO;
}
