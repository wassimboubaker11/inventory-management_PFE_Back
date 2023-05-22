package com.GestionDeStock.Controller;


import com.GestionDeStock.DTO.CommandeDTO;
import com.GestionDeStock.Entity.Commande;
import com.GestionDeStock.Entity.RequestData;
import com.GestionDeStock.Services.CommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1")
public class CommandeController {

    @Autowired
    private CommandeService commandeService;

    @PostMapping("/saveCommande/{idtier}/{quantity}/{montant}")
    public ResponseEntity<CommandeDTO> saveCom(@PathVariable("idtier") int idTier,
                                                    @PathVariable("quantity") int quantity,
                                                    @PathVariable("montant") Float montant,
                                               @RequestBody RequestData requestData) {
        return ResponseEntity.ok(commandeService.saveCom(idTier, requestData.getArticleID(), requestData.getCommande(), quantity, montant));
    }

    // http://localhost:8081/api/v1/saveCommande/{idtier}/{quantity}/{montant}

    @PostMapping("/saveCommande/{idtier}")
    public ResponseEntity<CommandeDTO> saveCommande(@PathVariable("idtier") int idTier,

                                                    @RequestBody RequestData requestData){

        return ResponseEntity.ok(commandeService.savecommande(idTier,requestData.getArticleID() ,requestData.getCommande()));
    }


    // http://localhost:8081/api/v1/saveCommandee/{idtier}/{idarticle}

    @PostMapping("/saveCommandee/{idtier}/{idarticle}")
    public ResponseEntity<CommandeDTO> saveCommande(@PathVariable("idtier") int idTier, @PathVariable("idarticle") int idarticle,@RequestBody Commande commande){

        return ResponseEntity.ok(commandeService.savecommandee(idTier,idarticle ,commande));
    }
}
