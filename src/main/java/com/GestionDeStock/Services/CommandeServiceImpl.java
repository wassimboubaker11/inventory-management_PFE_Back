package com.GestionDeStock.Services;

import com.GestionDeStock.DTO.CommandeDTO;
import com.GestionDeStock.Entity.Article;
import com.GestionDeStock.Entity.Commande;
import com.GestionDeStock.Entity.MVT;
import com.GestionDeStock.Entity.Tier;
import com.GestionDeStock.Repository.ArticleRepository;
import com.GestionDeStock.Repository.CommandeRepository;
import com.GestionDeStock.Repository.MVTRepository;
import com.GestionDeStock.Repository.TierRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommandeServiceImpl implements CommandeService{

    @Autowired
    private MVTRepository mvtRepository;

    @Autowired
    private CommandeRepository commandeRepository;

    @Autowired
    private TierRepository tierRepository;

    @Autowired
    private ArticleRepository articleRepository;

    private ModelMapper modelMapper = new ModelMapper();

    @Transactional
    @Override
    public CommandeDTO saveCom(int idTier, List<Integer> articleID, Commande commande, int quantity, Float montant) {
        Tier tier = tierRepository.findById(idTier).orElseThrow();

        List<Article> articles = articleRepository.findAllById(articleID);

        commande.setMontant(montant);
        commande.setQuantite(quantity);
        commande.setTier(tier);

        List<MVT> mvtList = new ArrayList<>();

        for (Article article : articles) {
            MVT mvt = new MVT();
            mvt.setArticle(article);
            mvt.setCommande(commande);
            mvtList.add(mvt);

            // Update the quantity of the article
            int newQuantity = article.getQuantite() + quantity;
            article.setQuantite(newQuantity);
            articleRepository.save(article);
        }

        commande.setMvtList(mvtList);

        Commande savedCommande = commandeRepository.save(commande);
        CommandeDTO commandeDTO = modelMapper.map(savedCommande, CommandeDTO.class);
        return commandeDTO;
    }


    @Transactional
    @Override
    public CommandeDTO savecommande(int idTier,List<Integer> articleID, Commande commande) {
        Tier tier = tierRepository.findById(idTier).orElseThrow();

        List<Article> articles = articleRepository.findAllById(articleID);

        for(Article article:articles){
            MVT mvt = new MVT();
            mvt.setArticle(article);
            mvt.setCommande(commande);
           //commande.getMvtList().add(mvt);
            mvtRepository.save(mvt);
        }

        Commande commande1 = commandeRepository.save(commande);
        CommandeDTO commandeDTO = modelMapper.map(commande1 , CommandeDTO.class);
        return commandeDTO;
    }


    @Transactional
    @Override
    public CommandeDTO savecommandee(int idTier, int idarticle, Commande commande) {
        Tier tier = tierRepository.findById(idTier).orElseThrow();

        Article article = articleRepository.findById(idarticle).orElseThrow();

            MVT mvt = new MVT();
            mvt.setArticle(article);
            mvt.setCommande(commande);
            commande.setMontant(article.getPrixachat()*commande.getQuantite());
            commande.setTier(tier);
            mvtRepository.save(mvt);

        Commande commande1 = commandeRepository.save(commande);
        CommandeDTO commandeDTO = modelMapper.map(commande1 , CommandeDTO.class);
        return commandeDTO;
    }
}

