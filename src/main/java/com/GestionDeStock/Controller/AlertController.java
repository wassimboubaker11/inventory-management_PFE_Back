package com.GestionDeStock.Controller;


import com.GestionDeStock.DTO.AlertDTO;
import com.GestionDeStock.DTO.DepotDTO;
import com.GestionDeStock.Entity.Alert;
import com.GestionDeStock.Entity.Depot;
import com.GestionDeStock.Services.AlertService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@CrossOrigin("*")
@RequestMapping("/api/v1")
@RestController
public class AlertController {

    @Autowired
    private AlertService alertService;

    //  http://localhost:8081/api/v1/addalert/{idarticle}

    @PostMapping("/addalert/{idarticle}")
    public AlertDTO addalert(@RequestBody Alert alert ,@PathVariable("idarticle") int idarticle){
        return  alertService.addalert(alert , idarticle);
    }


    //  http://localhost:8081/api/v1/getAllalert

    @GetMapping("/getAllalert")
    public List<AlertDTO> getAllalert(){
        return alertService.gettallalert();
    }

    // http://localhost:8081/api/v1/deletealertbyid/{idalert}


    @DeleteMapping("/deletealertbyid/{idalert}")
    public void deletealertbyid(@PathVariable ("idalert") int idalert){
        alertService.deleteAlert(idalert);
    }
}
