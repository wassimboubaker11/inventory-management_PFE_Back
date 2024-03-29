package com.GestionDeStock.Services;

import com.GestionDeStock.DTO.VariantDTO;
import com.GestionDeStock.Entity.Variant;

import java.util.List;
import java.util.Set;

public interface VariantService {

     VariantDTO addVariant(List<Integer> sousOptionIds, int articleId , int quantity,String nom);

     List<VariantDTO> getVariantbyarticle(int idarticle);

     void deletevariant(int idvariant);
}
