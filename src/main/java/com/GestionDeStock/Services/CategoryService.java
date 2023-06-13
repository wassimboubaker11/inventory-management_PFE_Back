package com.GestionDeStock.Services;

import com.GestionDeStock.DTO.CategoryDTO;
import com.GestionDeStock.Entity.Category;

import java.util.List;

public interface CategoryService {

    CategoryDTO savecategory (Category category);

    List<CategoryDTO> getAllcategory();

    void deletecategory(int idcategory);

    CategoryDTO getcategorybyid(int idcategory);

    CategoryDTO updateCategory(Category category , int idcategory);


}
