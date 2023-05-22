package com.GestionDeStock.Controller;


import com.GestionDeStock.DTO.AdminDTO;
import com.GestionDeStock.DTO.DepotDTO;
import com.GestionDeStock.Entity.Depot;
import com.GestionDeStock.Services.DepotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1")
public class DepotController {

    @Autowired
    private DepotService depotService;


    //  http://localhost:8081/api/v1/savedepot

    @PostMapping("/savedepot")
    public DepotDTO savedepot(@RequestBody Depot d){
        return  depotService.savedepot(d);
    }

    //  http://localhost:8081/api/v1/getAllDepot

    @GetMapping("/getAllDepot")
    public List<DepotDTO> getAllDepot(){
        return  depotService.getalldepot();
    }

    //  http://localhost:8081/api/v1/deletedepot/{iddepot}
    @DeleteMapping("/deletedepot/{iddepot}")
    public void deletedepot(@PathVariable int iddepot){
        depotService.deletedepot(iddepot);
    }

    //  http://localhost:8081/api/v1/editdepot/{iddepot}

    @PutMapping ("/editdepot/{iddepot}")
    public DepotDTO editdepot(@RequestBody Depot d , @PathVariable ("iddepot") int iddepot){
        return depotService.updatedepot(d , iddepot);
    }


    //  http://localhost:8081/api/v1/getdepotbyid/{iddepot}

    @GetMapping("/getdepotbyid/{iddepot}")
    public DepotDTO getadepotbyid(@PathVariable ("iddepot") int iddepot){
        return  depotService.getdepotbyid(iddepot);

    }
}
