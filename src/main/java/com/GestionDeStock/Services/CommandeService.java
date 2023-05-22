package com.GestionDeStock.Services;


import com.GestionDeStock.DTO.CommandeDTO;
import com.GestionDeStock.Entity.Article;
import com.GestionDeStock.Entity.Commande;

import java.util.List;

public interface CommandeService {

        CommandeDTO savecommande(int idTier , List<Integer> articleID,Commande commande );

        CommandeDTO savecommandee(int idTier , int idarticle,Commande commande);
        CommandeDTO saveCom(int idTier, List<Integer> articleID, Commande commande, int quantity, Float montant);
}
