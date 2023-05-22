package com.GestionDeStock.Controller;


import com.GestionDeStock.DTO.VariantDTO;
import com.GestionDeStock.Entity.Variant;
import com.GestionDeStock.Services.VariantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1")
public class VariantController {

    @Autowired
    private VariantService variantService;

    //  http://localhost:8081/api/v1/addvariant/{idarticle}

    @PostMapping("/addvariant/{idarticle}")
    public ResponseEntity<VariantDTO> addvariant(@RequestBody Map<String, Object> request, @PathVariable("idarticle") int articleId ){
        List<Integer> sousOptionIds = (List<Integer>) request.get("sousOptions");
        return ResponseEntity.ok(variantService.addVariant(sousOptionIds , articleId));
    }

    //  http://localhost:8081/api/v1/getVariantbyarticle/idarticle

    @GetMapping ("/getVariantbyarticle/{idarticle}")
    public ResponseEntity<List<VariantDTO>> getVariantbyarticle(@PathVariable("idarticle") int idarticle){
        return ResponseEntity.ok(variantService.getVariantbyarticle(idarticle));
        }
}
