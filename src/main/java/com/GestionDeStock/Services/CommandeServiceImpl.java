package com.GestionDeStock.Services;

import com.GestionDeStock.DTO.ArticleDTO;
import com.GestionDeStock.DTO.CommandeDTO;
import com.GestionDeStock.DTO.FactureDTO;
import com.GestionDeStock.DTO.MVTDTO;
import com.GestionDeStock.Entity.*;
import com.GestionDeStock.Repository.*;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CommandeServiceImpl implements CommandeService{

    @Autowired
    private VariantRepository variantRepository;
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
    public void saveCommandeclientwithvariant(List<OrderItem> orderItems, int clientId, String nom) {
        Tier client = tierRepository.findById(clientId).orElseThrow();


        List<MVT> mvts = new ArrayList<>();

        // Calculate the total quantity and amount
        int totalQuantity = 0;
        float totalAmount = 0.0f;
        for (OrderItem orderItem : orderItems) {
            int variantId = orderItem.getArticleId();
            int quantity = orderItem.getQuantity();

            Variant variant = variantRepository.findById(variantId).orElse(null);
            Article article = articleRepository.findArticleByVariantId(variantId);

            int quantityvariant = variant.getQuantity()-quantity;
            variant.setQuantity(quantityvariant);

            totalQuantity += quantity;
            totalAmount += (quantity * article.getPrixvente());

            int newQuantity = article.getQuantite() - quantity;
            article.setQuantite(newQuantity);

            MVT mvt = new MVT();
            mvt.setArticle(article);
            mvt.setQuantity(quantity);
            mvt.setNomVariant(variant.getNom());
            mvts.add(mvt);
            Article article1 = articleRepository.save(article);
            if (article1.getQuantite() > 8) {
                article1.setStatus(Type2.IN_STOCK);
            } else if (article1.getQuantite() == 0) {
                article1.setStatus(Type2.OUT_OF_STOCK);
            } else {
                article1.setStatus(Type2.LOW_STOCK);
            }
            articleRepository.save(article1);
            variantRepository.save(variant);
        }

        Commande commande = new Commande();
        commande.setQuantite(totalQuantity);
        commande.setMontant(totalAmount);
        commande.setNom(nom);
        commande.setTier(client);
        commande.setDate(LocalDateTime.now());
        commandeRepository.save(commande);

        for (MVT mvt : mvts) {
            mvt.setCommande(commande);
            mvt.setType1(Type1.SORTANT);
            mvtRepository.save(mvt);
        }
    }

    @Transactional
    @Override
    public void saveCommandeclient(List<OrderItem> orderItems, int clientId, String nom) {
        Tier client = tierRepository.findById(clientId).orElseThrow();


        List<MVT> mvts = new ArrayList<>();

        // Calculate the total quantity and amount
        int totalQuantity = 0;
        float totalAmount = 0.0f;

        for (OrderItem orderItem : orderItems) {

            int articleId = orderItem.getArticleId();
            int quantity = orderItem.getQuantity();

            Article article = articleRepository.findById(articleId).orElseThrow() ;

            totalQuantity += quantity;
            totalAmount += (quantity * article.getPrixvente());

            int newQuantity = article.getQuantite() - quantity;
            article.setQuantite(newQuantity);

            MVT mvt = new MVT();
            mvt.setArticle(article);
            mvt.setQuantity(quantity);
            mvts.add(mvt);

            articleRepository.save(article);



        }

        Commande commande = new Commande();
        commande.setQuantite(totalQuantity);
        commande.setMontant(totalAmount);
        commande.setNom(nom);
        commande.setTier(client);
        commande.setDate(LocalDateTime.now());
        commandeRepository.save(commande);

        for (MVT mvt : mvts) {
            mvt.setCommande(commande);
            mvt.setType1(Type1.SORTANT);
            mvtRepository.save(mvt);
        }
    }


    @Override
    public void saveCommandefournisseur(List<OrderItem> orderItems, int clientId, String nom) {
        Tier client = tierRepository.findById(clientId).orElseThrow();


        List<MVT> mvts = new ArrayList<>();

        // Calculate the total quantity and amount
        int totalQuantity = 0;
        float totalAmount = 0.0f;

        for (OrderItem orderItem : orderItems) {

            int articleId = orderItem.getArticleId();
            int quantity = orderItem.getQuantity();

            Article article = articleRepository.findById(articleId).orElse(null);

            totalQuantity += quantity;
            totalAmount += (quantity * article.getPrixachat());

            int newQuantity = article.getQuantite() + quantity;
            article.setQuantite(newQuantity);

            MVT mvt = new MVT();
            mvt.setArticle(article);
            mvt.setQuantity(quantity);
            mvts.add(mvt);

            Article article1 = articleRepository.save(article);
            if (article1.getQuantite() > 8) {
                article1.setStatus(Type2.IN_STOCK);
            } else if (article1.getQuantite() == 0) {
                article1.setStatus(Type2.OUT_OF_STOCK);
            } else {
                article1.setStatus(Type2.LOW_STOCK);
            }
            articleRepository.save(article1);

        }
        Commande commande = new Commande();
        commande.setQuantite(totalQuantity);
        commande.setMontant(totalAmount);
        commande.setNom(nom);
        commande.setTier(client);
        commande.setDate(LocalDateTime.now());
        commandeRepository.save(commande);

        for (MVT mvt : mvts) {
            mvt.setCommande(commande);
            mvt.setType1(Type1.ENTRANT);
            mvtRepository.save(mvt);
        }
    }



    @Override
    public List<CommandeDTO> getAllCommandeClient() {
        List<Tier> tiers = tierRepository.findAllByType(Type.CLIENT);

        List<Integer> clientIds = new ArrayList<>();
        List<Commande> commandes = new ArrayList<>();

        for (Tier tier : tiers) {
            clientIds.add(tier.getIdtier());
        }
        for (Integer integer : clientIds) {
            List<Commande> tierCommandes = commandeRepository.findAllByTierId(integer);
            commandes.addAll(tierCommandes);
        }

        List<CommandeDTO> commandeDTOS = new ArrayList<>();
        for (Commande commande : commandes) {
            CommandeDTO commandeDTO = modelMapper.map(commande, CommandeDTO.class);
            if (commande.getFacture() != null) {
                commandeDTO.setFactureDTO(modelMapper.map(commande.getFacture(), FactureDTO.class));
            }
            commandeDTOS.add(commandeDTO);
        }
        return commandeDTOS;
    }

    @Override
    public List<CommandeDTO> getAllCommandeFournisseur() {
        List<Tier> tiers = tierRepository.findAllByType(Type.FOURNISSEUR);

        List<Integer> clientIds = new ArrayList<>();
        List<Commande> commandes = new ArrayList<>();

        for (Tier tier : tiers) {
            clientIds.add(tier.getIdtier());
        }
        for (Integer integer : clientIds) {
            List<Commande> tierCommandes = commandeRepository.findAllByTierId(integer);
            commandes.addAll(tierCommandes);
        }

        List<CommandeDTO> commandeDTOS = new ArrayList<>();
        for (Commande commande : commandes) {
            CommandeDTO commandeDTO = modelMapper.map(commande, CommandeDTO.class);
            if (commande.getFacture() != null) {
                commandeDTO.setFactureDTO(modelMapper.map(commande.getFacture(), FactureDTO.class));
            }
            commandeDTOS.add(commandeDTO);
        }
        return commandeDTOS;
    }

    @Transactional
    @Override
    public void deletecommande(int idcommande) {
        List<MVT> mvts = mvtRepository.findMVTByIdcommande(idcommande);
        for (MVT mvt : mvts) {
            Article article = mvt.getArticle();
            article.setQuantite(article.getQuantite() + mvt.getQuantity());
            articleRepository.save(article);
        }
        commandeRepository.deleteById(idcommande);
    }

    @Override
    public CommandeDTO getCommandeById(int idcommande) {
        Commande commande = commandeRepository.findById(idcommande).orElse(null);

        if (commande == null) {
            throw new EntityNotFoundException("Commande not found"); // Example exception handling
        }

        CommandeDTO commandeDTO = modelMapper.map(commande, CommandeDTO.class);

        if (commande.getFacture() != null) {
            commandeDTO.setFactureDTO(modelMapper.map(commande.getFacture(), FactureDTO.class));
        }

        List<MVTDTO> mvtList = new ArrayList<>();
        for (MVT mvt : commande.getMvtList()) {
            MVTDTO mvtDTO = modelMapper.map(mvt, MVTDTO.class);
            mvtDTO.setArticleDTO(modelMapper.map(mvt.getArticle(), ArticleDTO.class));
            mvtDTO.setCommandeDTO(modelMapper.map(mvt.getCommande(), CommandeDTO.class));
            mvtList.add(mvtDTO);
        }
        commandeDTO.setMvtList(mvtList);

        return commandeDTO;
    }

    @Override
    public void deletecommandefournisseur(int idcommande) {
        List<MVT> mvts = mvtRepository.findMVTByIdcommande(idcommande);
        for (MVT mvt : mvts) {
            Article article = mvt.getArticle();
            article.setQuantite(article.getQuantite() - mvt.getQuantity());
            articleRepository.save(article);
        }
        commandeRepository.deleteById(idcommande);
    }


    @Override
    public void saveCommande( List<OrderItem> orderItems) {
        List<MVT> mvts = new ArrayList<>();

        // Calculate the total quantity and amount
        int totalQuantity = 0;
        float totalAmount = 0.0f;

        for (OrderItem orderItem : orderItems) {

            int articleId = orderItem.getArticleId();
            int quantity = orderItem.getQuantity();

            Article article = articleRepository.findById(articleId).orElse(null);

            totalQuantity += quantity;
            totalAmount += (quantity * article.getPrixvente());

            int newQuantity = article.getQuantite() - quantity;
            article.setQuantite(newQuantity);

            MVT mvt = new MVT();
            mvt.setArticle(article);
            mvts.add(mvt);

            articleRepository.save(article);



        }

        Commande commande = new Commande();
        commande.setQuantite(totalQuantity);
        commande.setMontant(totalAmount);

        commande.setDate(LocalDateTime.now());
        commandeRepository.save(commande);

        for (MVT mvt : mvts) {
            mvt.setCommande(commande);
            mvt.setType1(Type1.ENTRANT);
            mvtRepository.save(mvt);
        }

    }


}

