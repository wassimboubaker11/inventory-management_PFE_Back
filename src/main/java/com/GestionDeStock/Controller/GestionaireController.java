package com.GestionDeStock.Controller;


import com.GestionDeStock.Entity.Admin;
import com.GestionDeStock.Entity.Gestionaire;
import com.GestionDeStock.Services.GestionaireService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/user")
public class GestionaireController {

    @Autowired
    GestionaireService gestionaireService;

    @RequestMapping(value = "/register", method = RequestMethod.POST,consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Gestionaire> registerPartner(@RequestPart("user") String DTO,
                                                       @RequestPart("photo") MultipartFile photo)throws IOException {

        return ResponseEntity.ok(gestionaireService.registerGestionaire(DTO,photo));
    }
}
