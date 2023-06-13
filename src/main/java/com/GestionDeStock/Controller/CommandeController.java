package com.GestionDeStock.Controller;


import com.GestionDeStock.DTO.CommandeDTO;
import com.GestionDeStock.Entity.Commande;
import com.GestionDeStock.Entity.OrderItem;
import com.GestionDeStock.Entity.OrderRequest;
import com.GestionDeStock.Entity.RequestData;
import com.GestionDeStock.Services.CommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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



    // http://localhost:8081/api/v1/saveCommandeclient/{clientId}/{nom}
    @PostMapping("/saveCommandeclient/{clientId}/{nom}")
    public ResponseEntity<?> saveCommandeclient(@RequestBody List<OrderItem> orderItems, @PathVariable("clientId") int clientId, @PathVariable("nom") String nom){
        commandeService.saveCommandeclient(orderItems,clientId , nom);
        return new ResponseEntity<>("Commande saved successfully.", HttpStatus.OK);
    }



    // http://localhost:8081/api/v1/saveCommandefournisseur/{fournisseurtId}/{nom}
    @PostMapping("/saveCommandefournisseur/{fournisseurtId}/{nom}")
    public ResponseEntity<?> saveCommandefournisseur(@RequestBody List<OrderItem> orderItems, @PathVariable("fournisseurtId") int fournisseurtId, @PathVariable("nom") String nom){
        commandeService.saveCommandefournisseur(orderItems,fournisseurtId , nom);
        return new ResponseEntity<>("Commande saved successfully.", HttpStatus.OK);
    }


    // http://localhost:8081/api/v1/getAllCommandeClient
    @GetMapping("/getAllCommandeClient")
    public ResponseEntity<List<CommandeDTO>>getAllCommandeClient(){
        return ResponseEntity.ok(commandeService.getAllCommandeClient());
    }

    // http://localhost:8081/api/v1/getAllCommandeFournisseur
    @GetMapping("/getAllCommandeFournisseur")
    public ResponseEntity<List<CommandeDTO>> getAllCommandeFournisseur(){
        return ResponseEntity.ok(commandeService.getAllCommandeFournisseur());
    }

    // http://localhost:8081/api/v1/deletecommandefournisseur/{idcommande}
    @DeleteMapping("/deletecommandefournisseur/{idcommande}")
    public void deletecommandefournisseur (@PathVariable("idcommande") int idcommande){
        commandeService.deletecommandefournisseur(idcommande);
    }

    // http://localhost:8081/api/v1/deleteCommande/{idcommande}
    @DeleteMapping("/deleteCommande/{idcommande}")
    public void deletecommande (@PathVariable("idcommande") int idcommande){
        commandeService.deletecommande(idcommande);
    }


    // http://localhost:8081/api/v1/saveComman
    @PostMapping("/saveComman")
    public ResponseEntity<?> saveComman(@RequestBody List<OrderItem> orderItems){
        // List<OrderItem> orderItems = orderRequest.getOrderItems();
        commandeService.saveCommande(orderItems);
        return new ResponseEntity<>("Commande saved successfully.", HttpStatus.OK);
    }

    // http://localhost:8081/api/v1/getCommandeById/{idcommande}
    @GetMapping("/getCommandeById/{idcommande}")
    public ResponseEntity<?> getCommandeById(@PathVariable("idcommande") int idcommande){
        return ResponseEntity.ok(commandeService.getCommandeById(idcommande));
    }
}
