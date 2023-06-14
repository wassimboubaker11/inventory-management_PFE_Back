package com.GestionDeStock.Services;


import com.GestionDeStock.DTO.VariantDTO;
import com.GestionDeStock.Entity.Article;
import com.GestionDeStock.Entity.SousOption;
import com.GestionDeStock.Entity.Variant;
import com.GestionDeStock.Repository.ArticleRepository;
import com.GestionDeStock.Repository.Sous_OptionRepository;
import com.GestionDeStock.Repository.VariantRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class VariantServiceImpl implements VariantService{

    @Autowired
    private VariantRepository variantRepository;

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private Sous_OptionRepository sous_optionRepository;

    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public VariantDTO addVariant(List<Integer> sousOptionIds, int articleId , int quantity , String nom) {

        Article article = articleRepository.findById(articleId).get();


        List<SousOption> sousOptions = sous_optionRepository.findAllById(sousOptionIds);


        int totalVariantQuantity = variantRepository.sumQuantityByArticleId(articleId); // calculer all quantity variants

        int remainingQuantity = article.getQuantite() - totalVariantQuantity; // quantity disponible variant

        if (quantity > remainingQuantity) {
            throw new IllegalArgumentException("Requested quantity exceeds available quantity.");
        }


        Variant variant = new Variant();
        variant.setSousOptions(sousOptions);
        variant.setArticle(article);
        variant.setQuantity(quantity);
        variant.setNom(nom);

        Variant variant1 =variantRepository.save(variant);

        for(SousOption sousOption : sousOptions){
            sousOption.getVariants().add(variant);
            sous_optionRepository.save(sousOption);
        }

        VariantDTO variantDTO = modelMapper.map(variant1 ,VariantDTO.class );


     return variantDTO;
    }



//    @Override
//    public List<VariantDTO> getVariantbyarticle(int idarticle) {
//
//        Article article = articleRepository.findById(idarticle).get();
//
//        List<Variant> variants = variantRepository.findVariantByArticle(article);
//        List<VariantDTO> variantDTO = new ArrayList<>();
//        for (Variant variantss : variants ){
//            variantDTO.add(modelMapper.map(variantss , VariantDTO.class));
//        }
//
//
//        return variantDTO;
//
//    }

    @Override
    public List<VariantDTO> getVariantbyarticle(int idarticle) {

        Article article = articleRepository.findById(idarticle).orElse(null);

        List<Variant> variants = variantRepository.findVariantByArticle(article);
        List<VariantDTO> variantDTOs = new ArrayList<>();

        if (article != null) {
            String picture = article.getPicture(); // Get the picture from the article
            float prixvente = article.getPrixvente(); // Get the selling price from the article

            for (Variant variant : variants) {
                VariantDTO variantDTO = modelMapper.map(variant, VariantDTO.class);
                variantDTO.setPicture(picture); // Set the picture for each variant
                variantDTO.setPrixvente(prixvente); // Set the selling price for each variant
                variantDTOs.add(variantDTO);
            }
        }

        return variantDTOs;
    }

    @Override
    public void deletevariant(int idvariant) {
        variantRepository.deleteById(idvariant);
    }


}
