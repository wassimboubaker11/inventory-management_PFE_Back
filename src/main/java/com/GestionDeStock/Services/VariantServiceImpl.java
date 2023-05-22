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
    public VariantDTO addVariant(List<Integer> sousOptionIds, int articleId) {

        Article article = articleRepository.findById(articleId).get();

       // List<SousOption> sousOptionsList = sous_optionRepository.findAllById(sousOptionIds);

        List<SousOption> sousOptions = sous_optionRepository.findAllById(sousOptionIds);

        Variant variant = new Variant();
        variant.setSousOptions(sousOptions);
        variant.setArticle(article);
        Variant variant1 =variantRepository.save(variant);
        for(SousOption sousOption : sousOptions){
            sousOption.getVariants().add(variant);
            sous_optionRepository.save(sousOption);
        }

        VariantDTO variantDTO = modelMapper.map(variant1 ,VariantDTO.class );


     return variantDTO;
    }



    @Override
    public List<VariantDTO> getVariantbyarticle(int idarticle) {

        Article article = articleRepository.findById(idarticle).get();

        List<Variant> variants = variantRepository.findVariantByArticle(article);
        List<VariantDTO> variantDTO = new ArrayList<>();
        for (Variant variantss : variants ){
            variantDTO.add(modelMapper.map(variantss , VariantDTO.class));
        }


        return variantDTO;

    }


}
