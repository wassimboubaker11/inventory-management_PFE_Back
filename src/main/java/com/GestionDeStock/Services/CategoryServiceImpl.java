package com.GestionDeStock.Services;


import com.GestionDeStock.DTO.CategoryDTO;
import com.GestionDeStock.Entity.Category;
import com.GestionDeStock.Repository.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{
    private ModelMapper modelMapper = new ModelMapper();

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public CategoryDTO savecategory(Category category) {
        Category category1 = categoryRepository.save(category);
        CategoryDTO categoryDTO = modelMapper.map(category1 , CategoryDTO.class);
        return categoryDTO;
    }

    @Override
    public List<CategoryDTO> getAllcategory() {
        List<Category> category = categoryRepository.findAll();
        List<CategoryDTO> categoryDTO = new ArrayList<>();
        for( Category cat : category){
            categoryDTO.add(modelMapper.map(cat , CategoryDTO.class));
        }
        return categoryDTO;
    }


    @Override
    public void deletecategory(int idcategory) {
          categoryRepository.deleteById(idcategory);

    }

    @Override
    public CategoryDTO getcategorybyid(int idcategory) {
        Category category = categoryRepository.findById(idcategory).get();
        CategoryDTO categoryDTO = modelMapper.map(category , CategoryDTO.class);
        return categoryDTO;
    }

    @Override
    public CategoryDTO updateCategory(Category category, int idcategory) {
        Category category1 = categoryRepository.findById(idcategory).get();

        category1.setNom(category1.getNom());

        Category category2= categoryRepository.save(category1);

        CategoryDTO categoryDTO = modelMapper.map(category2 , CategoryDTO.class);
        return categoryDTO;
    }




}
