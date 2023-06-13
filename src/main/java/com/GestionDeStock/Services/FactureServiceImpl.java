package com.GestionDeStock.Services;

import com.GestionDeStock.DTO.FactureDTO;
import com.GestionDeStock.Entity.Commande;
import com.GestionDeStock.Entity.Facture;
import com.GestionDeStock.Repository.CommandeRepository;
import com.GestionDeStock.Repository.FactureRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class FactureServiceImpl implements FactureService{

    private ModelMapper modelMapper = new ModelMapper();
    @Autowired
    private FactureRepository factureRepository;

    @Autowired
    private CommandeRepository commandeRepository ;


    @Transactional
    @Override
    public FactureDTO saveFacture( int idcommande) {
        Commande commande = commandeRepository.findById(idcommande).orElse(null);
        if (commande.getFacture() != null) {
            throw new IllegalStateException("Commande already has a facture");
        }

        Facture facture = new Facture();
        facture.setDateFacture(LocalDate.now());
        facture.setTva(19);
        facture.setReference(facture.generateID());
        facture.setHt(commande.getMontant());
        facture.setTtc(facture.calculTotal(facture.getHt(), facture.getTva()));
        facture.setTotalLettre(facture.totalEnLettre(facture.getTtc()));

        Facture facture1 = factureRepository.save(facture);
        commande.setFacture(facture1);
        commandeRepository.save(commande);

        FactureDTO factureDTO = modelMapper.map(facture1 , FactureDTO.class);
        return factureDTO;
    }

    @Override
    public FactureDTO getFacturebyidcommande(int idcommande) {

        Facture facture = factureRepository.findFactureByCommandIdJPQL(idcommande);
        FactureDTO factureDTO = modelMapper.map(facture , FactureDTO.class);
            return factureDTO;
    }

}
