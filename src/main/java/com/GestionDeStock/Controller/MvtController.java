package com.GestionDeStock.Controller;


import com.GestionDeStock.DTO.MVTDTO;
import com.GestionDeStock.DTO.OptionDTO;
import com.GestionDeStock.Services.MVTService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1")
public class MvtController {


    @Autowired
    private MVTService mvtService;



    // http://localhost:8081/api/v1/getAllMVTbyIdCommande/{idcommande}

    @GetMapping("/getAllMVTbyIdCommande/{idcommande}")
    public ResponseEntity<List<MVTDTO>> getAllMVTbyIdCommande(@PathVariable("idcommande") int idcommande){
        return ResponseEntity.ok(mvtService.getAllMVTbyIdCommande(idcommande));
    }

    // http://localhost:8081/api/v1/getAllMVT

    @GetMapping("/getAllMVT")
    public ResponseEntity<List<MVTDTO>> getAllMVT(){
        return ResponseEntity.ok(mvtService.getAllMVT());
    }
}
