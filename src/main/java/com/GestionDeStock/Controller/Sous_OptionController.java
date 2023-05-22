package com.GestionDeStock.Controller;


import com.GestionDeStock.DTO.SousOptionDTO;
import com.GestionDeStock.Entity.SousOption;
import com.GestionDeStock.Services.Sous_OptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1")
public class Sous_OptionController {


    @Autowired
    private Sous_OptionService sous_optionService;

    //  http://localhost:8081/api/v1/savesousvariant/{idoption}

    @PostMapping("/savesousvariant/{idoption}")
    public ResponseEntity<SousOptionDTO> savesous_option(@RequestBody SousOption sous_option , @PathVariable("idoption") int idoption){
        return ResponseEntity.ok(sous_optionService.saveSousOption(sous_option , idoption));
    }

    // http://localhost:8081/api/v1/gelAllSousOption
    @GetMapping("gelAllSousOption")
    public ResponseEntity<List<SousOptionDTO>> getAllSousOption (){
        return ResponseEntity.ok(sous_optionService.getAllSousOption());
    }
}
