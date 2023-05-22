package com.GestionDeStock.Controller;


import com.GestionDeStock.DTO.CategoryDTO;
import com.GestionDeStock.Entity.Category;
import com.GestionDeStock.Services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    // http://localhost:8081/api/v1/savecategory

    @PostMapping("/savecategory")
    public ResponseEntity<CategoryDTO> savecategory(@RequestBody Category category){
        return ResponseEntity.ok(categoryService.savecategory(category));
    }


    // http://localhost:8081/api/v1/getAllcategory
    @GetMapping("getAllcategory")
    public ResponseEntity<List<CategoryDTO>> getAllcategory(){
        return ResponseEntity.ok(categoryService.getAllcategory());
    }

    // http://localhost:8081/api/v1/deletecategorybyid/{idcategory}
    @DeleteMapping("deletecategorybyid/{idcategory}")
    public void deletecategorybyid(@PathVariable("idcategory") int idcategory){
        categoryService.deletecategory(idcategory);
    }
}
