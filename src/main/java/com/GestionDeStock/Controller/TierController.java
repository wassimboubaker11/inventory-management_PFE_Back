package com.GestionDeStock.Controller;


import com.GestionDeStock.DTO.ArticleDTO;
import com.GestionDeStock.DTO.TierDTO;
import com.GestionDeStock.Services.TierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1")
public class TierController {

    @Autowired
    private TierService tierService;


    //  http://localhost:8081/api/v1/saveFournisseur

    @RequestMapping(value = "/saveFournisseur", method = RequestMethod.POST,consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<TierDTO> saveTierFournisseur (@RequestPart("fournisseur")String fournisseur , @RequestPart("photo")MultipartFile photo) throws IOException {
        return ResponseEntity.ok(tierService.saveTierFournisseur(fournisseur , photo));
    }


    //  http://localhost:8081/api/v1/saveClient
    @RequestMapping(value = "/saveClient", method = RequestMethod.POST,consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<TierDTO> saveTierClient (@RequestPart("client")String client , @RequestPart("photo")MultipartFile photo) throws IOException {
        return ResponseEntity.ok(tierService.saveTierClient(client , photo));
    }

    //  http://localhost:8081/api/v1/getAllFounisseur

    @GetMapping("/getAllFounisseur")
    public ResponseEntity<List<TierDTO>> getAllTierfournisseur(){
        return ResponseEntity.ok(tierService.getAllTierFounisseur());
    }

    //  http://localhost:8081/api/v1/getAllClient

    @GetMapping("/getAllClient")
    public ResponseEntity<List<TierDTO>> getAllTierClient(){
        return ResponseEntity.ok(tierService.getAllTierClient());
    }

    //  http://localhost:8081/api/v1/deleteClient/{idclient}

    @DeleteMapping("/deleteClient/{idclient}")
    public void deleteclient(@PathVariable("idclient") int idclient){
        tierService.deleteClient(idclient);
    }

    //  http://localhost:8081/api/v1/deleteFounisseur/{idclient}

    @DeleteMapping("/deleteFounisseur/{idFounisseur}")
    public void deleteFounisseur(@PathVariable("idFounisseur") int idFounisseur){
        tierService.deleteFounisseur(idFounisseur);
    }

    //  http://localhost:8081/api/v1/updateClient/{idclient}

    @RequestMapping(value = "/updateClient/{idclient}", method = RequestMethod.PUT, consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<TierDTO> updateClient(@PathVariable("idclient") int idclient, @RequestPart("client") String client, @RequestPart("photo") MultipartFile photo) throws IOException {
        TierDTO tierDTO  = tierService.updateClient(idclient , client , photo);
        return ResponseEntity.ok(tierDTO);
    }


    //  http://localhost:8081/api/v1/updateFounisseur/{idFounisseur}

    @RequestMapping(value = "/updateFounisseur/{idFounisseur}", method = RequestMethod.PUT, consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<TierDTO> updateFounisseur(@PathVariable("idFounisseur") int idFounisseur, @RequestPart("fournisseur") String Founisseur, @RequestPart("photo") MultipartFile photo) throws IOException {
        TierDTO tierDTO  = tierService.updateFounisseur(idFounisseur, Founisseur, photo);
        return ResponseEntity.ok(tierDTO);
    }

    //  http://localhost:8081/api/v1/GetClient_FournisseurbyID/{idtier}

    @GetMapping("/GetClient_FournisseurbyID/{idtier}")
    public ResponseEntity<TierDTO> getClient_FournisseurbyID(@PathVariable ("idtier") int idtier){
        return ResponseEntity.ok(tierService.getClient_FournisseurbyID(idtier));
    }


}
