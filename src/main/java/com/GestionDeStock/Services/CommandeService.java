package com.GestionDeStock.Services;


import com.GestionDeStock.DTO.CommandeDTO;
import com.GestionDeStock.Entity.Article;
import com.GestionDeStock.Entity.Commande;
import com.GestionDeStock.Entity.OrderItem;

import java.util.List;

public interface CommandeService {




        public void saveCommandeclient(List<OrderItem> orderItems, int clientId, String nom);

        public void saveCommande( List<OrderItem> orderItems);

        public void saveCommandefournisseur(List<OrderItem> orderItems, int clientId, String nom);

        List<CommandeDTO> getAllCommandeClient();

        List<CommandeDTO> getAllCommandeFournisseur();

        void deletecommande(int idcommande);

        CommandeDTO getCommandeById(int idcommande);

        void deletecommandefournisseur(int idcommande);
}
