package com.GestionDeStock.Services;


import com.GestionDeStock.DTO.ArticleDTO;
import com.GestionDeStock.Entity.Article;
import com.GestionDeStock.Entity.Category;
import com.GestionDeStock.Entity.Depot;
import com.GestionDeStock.Repository.ArticleRepository;
import com.GestionDeStock.Repository.CategoryRepository;
import com.GestionDeStock.Repository.DepotRepository;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    private ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    @Autowired
    DepotRepository depotRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    ArticleRepository articleRepository;

    private ModelMapper modelMapper = new ModelMapper();

    @Autowired  private FileLoaderService fileLoaderService;

    @Override
    public ArticleDTO savearticle(String article, int iddepot ,int idcategory  , MultipartFile picture) throws IOException {


        Depot depot = depotRepository.findById(iddepot).get();

        Category category = categoryRepository.findById(idcategory).get();

        ArticleDTO articleDTO = objectMapper.readValue(article , ArticleDTO.class);

        articleDTO.setPicture(fileLoaderService.loadFile(picture));

        Article article1 = modelMapper.map(articleDTO , Article.class);
        article1.setCategory(category);

         Article article2 = articleRepository.save(article1);

         ArticleDTO articleDTO1 = modelMapper.map(article2 , ArticleDTO.class);

        depot.getArticle().add(article1);
        depotRepository.save(depot);



        return articleDTO1;





    }

    @Override
    public List<ArticleDTO> getAllArticle() {
        List<Article> articleList = articleRepository.findAll();
        List<ArticleDTO> articleDTOS = new ArrayList<>();
        for(Article article : articleList){
            articleDTOS.add(modelMapper.map(article , ArticleDTO.class));
        }
        return articleDTOS;
    }

    @Override
    public void deletearticle(int idartilce) {
         articleRepository.deleteById(idartilce);
    }

    @Override
    public ArticleDTO updatearticle(int idarticle, String article, MultipartFile picture)throws IOException{

        Article article1 = articleRepository.findById(idarticle).orElseThrow();

        ArticleDTO articleDTO = objectMapper.readValue(article, ArticleDTO.class);

        article1.setNom(articleDTO.getNom());
        article1.setRef_article(articleDTO.getRef_article());
        article1.setNum_serie(articleDTO.getNum_serie());
        article1.setCode_barre(articleDTO.getCode_barre());
        article1.setMarque(articleDTO.getMarque());
        article1.setPrixachat(articleDTO.getPrixachat());
        article1.setPrixvente(articleDTO.getPrixvente());
        article1.setQuantite(articleDTO.getQuantite());
        article1.setDescription(articleDTO.getDescription());
        article1.setStatus(articleDTO.isStatus());

        article1.setPicture(fileLoaderService.loadFile(picture));

        Article article3 = articleRepository.save(article1);

        ArticleDTO articleDTO1 = modelMapper.map(article3 , ArticleDTO.class);



        return articleDTO1;

    }

    @Override
    public ArticleDTO saveearticle(String article, MultipartFile picturee) throws IOException {

        ArticleDTO articleDTO = objectMapper.readValue(article , ArticleDTO.class);

        articleDTO.setPicture(fileLoaderService.loadFile(picturee));

        Article article1 = modelMapper.map(articleDTO , Article.class);

        Article article2 = articleRepository.save(article1);

        ArticleDTO articleDTO1 = modelMapper.map(article2 , ArticleDTO.class);




        return articleDTO1;
    }

    @Override
    public ArticleDTO getarticlebyid(int idarticle) {
        Article article = articleRepository.findById(idarticle).get();
        ArticleDTO articleDTO = modelMapper.map(article , ArticleDTO.class);
        return  articleDTO;
    }

    @Override
    public List<ArticleDTO> getallarticlebyiddepot(int iddepot) {
        List<Article> articles = articleRepository.findByDepotId(iddepot);
        List<ArticleDTO> articleDTOS = new ArrayList<>();
        for(Article article : articles){
            articleDTOS.add(modelMapper.map(article , ArticleDTO.class));
        }
        return articleDTOS;
    }
}
