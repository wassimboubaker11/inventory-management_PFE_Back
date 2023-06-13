package com.GestionDeStock.Controller;


import com.GestionDeStock.DTO.GestionaireDTO;

import com.GestionDeStock.Entity.Gestionaire;
import com.GestionDeStock.Services.GestionaireService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/user")
public class GestionaireController {

    @Autowired
    GestionaireService gestionaireService;


    // http://localhost:8081/api/v1/user/register/{idadmin}


    @RequestMapping(value = "/register/{idadmin}", method = RequestMethod.POST,consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<GestionaireDTO> registerPartner(@RequestPart("user") String DTO,
                                                       @PathVariable("idadmin")  int idadmin,
                                                       @RequestPart("photo") MultipartFile photo)throws IOException {

        return ResponseEntity.ok(gestionaireService.registerGestionaire(DTO, idadmin,photo));
    }

    //  http://localhost:8081/api/v1/user/getAllGestionairebyidadmin/{idadmin}


    @GetMapping("/getAllGestionairebyidadmin/{idadmin}")
    public List<GestionaireDTO> getGestionairesByAdminId(@PathVariable ("idadmin") Integer adminId){
        return gestionaireService.getGestionairesByAdminId(adminId);
    }

    //  http://localhost:8081/api/v1/user/valid/{id}
    @PutMapping("/valid/{idg}")
    public Gestionaire validegestionaire (@PathVariable ("idg") int idg , @RequestBody Gestionaire gestionaire)throws MessagingException, jakarta.mail.MessagingException, IOException{
        return gestionaireService.validegestionaire(idg , gestionaire);
    }

    //  http://localhost:8081/api/v1/user/getgestionairebyemail/{email}
    @GetMapping("getgestionairebyemail/{email}")
    public GestionaireDTO getgestionairebyemail(@PathVariable ("email") String email){
        return gestionaireService.getgestionairebyemail(email);
    }


    //  http://localhost:8081/api/v1/user/deletegestionaire/{idgestionaire}
    @DeleteMapping ("deletegestionaire/{idgestionaire}")
    public void deletegestionaire(@PathVariable ("idgestionaire") int idgestionaire){
         gestionaireService.deletegestionairebyid(idgestionaire);
    }
}
