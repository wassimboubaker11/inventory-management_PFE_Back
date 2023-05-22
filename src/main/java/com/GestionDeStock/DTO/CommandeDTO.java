package com.GestionDeStock.DTO;

import com.GestionDeStock.Entity.Commande;
import com.GestionDeStock.Entity.MVT;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommandeDTO {

    private int idcommande;

    private int quantite;

    private Float montant;


}
