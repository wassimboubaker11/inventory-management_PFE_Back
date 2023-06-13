package com.GestionDeStock.Controller;


import com.GestionDeStock.DTO.OptionDTO;
import com.GestionDeStock.Entity.Option;
import com.GestionDeStock.Services.OptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1")
public class OptionController {

    @Autowired
    private OptionService optionService;

    //  http://localhost:8081/api/v1/seveoption

    @PostMapping("/seveoption")
    public ResponseEntity<OptionDTO> saveoption(@RequestBody Option o){
        return ResponseEntity.ok(optionService.saveoption(o));
    }

    // http://localhost:8081/api/v1/getAlloption

    @GetMapping("/getAlloption")
    public ResponseEntity<List<OptionDTO>> getAlloption(){
        return ResponseEntity.ok(optionService.getAlloption());
    }

    // http://localhost:8081/api/v1/deleteoption/{}

    @DeleteMapping("/deleteoption/{idoption}")
    public void  deleteoption(@PathVariable ("idoption") int idoption){
        optionService.deleteoption(idoption);
    }

    // http://localhost:8081/api/v1/getoptiobById
    @GetMapping("/getoptiobById/{idoption}")
    public ResponseEntity<OptionDTO> getOptionById(@PathVariable("idoption") int idoption){
        return ResponseEntity.ok( optionService.getOptionByID(idoption));
    }


    // http://localhost:8081/api/v1/updateOption/{idoption}
    @PutMapping("/updateOption/{idoption}")
    public ResponseEntity<OptionDTO> updateOption(@RequestBody Option option,@PathVariable ("idoption") int idoption){
        return  ResponseEntity.ok(optionService.updateOption(option , idoption));
    }
}
