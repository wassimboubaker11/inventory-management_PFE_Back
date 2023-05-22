package com.GestionDeStock.Services;

import com.GestionDeStock.DTO.TierDTO;
import com.GestionDeStock.Entity.Tier;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface TierService {

    TierDTO saveTierFournisseur(String tier , MultipartFile photo)throws IOException;
    TierDTO saveTierClient(String tier , MultipartFile photo)throws IOException;


    List<TierDTO> getAllTierFounisseur();
    List<TierDTO> getAllTierClient();


    void deleteClient(int idclient);

    void deleteFounisseur(int idfounisseur);

    TierDTO updateClient(int idClient , String client, MultipartFile phoro)throws IOException;

    TierDTO updateFounisseur(int idFounisseur ,String Founisseur  , MultipartFile photo)throws IOException;

    TierDTO getClient_FournisseurbyID(int id);

}
