package com.GestionDeStock.Services;

import com.GestionDeStock.DTO.TierDTO;
import com.GestionDeStock.Entity.Tier;
import com.GestionDeStock.Entity.Type;
import com.GestionDeStock.Repository.TierRepository;
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
public class TierServiceImpl implements TierService{
    private ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    private ModelMapper modelMapper = new ModelMapper();
    @Autowired
    private TierRepository  tierRepository;

    @Autowired
    private FileLoaderService fileLoaderService;

    @Override
    public TierDTO saveTierFournisseur(String fourniseur , MultipartFile photo)throws IOException {

        Tier tier1 = objectMapper.readValue(fourniseur , Tier.class);
        tier1.setPhoto(fileLoaderService.loadFile(photo));
        tier1.setType(Type.FOURNISSEUR);
        Tier tier2 = tierRepository.save(tier1);
        TierDTO tierDTO = modelMapper.map(tier2 , TierDTO.class);
        return tierDTO;

    }

    @Override
    public TierDTO saveTierClient(String client, MultipartFile photo) throws IOException {
        Tier tier1 = objectMapper.readValue(client , Tier.class);
        tier1.setPhoto(fileLoaderService.loadFile(photo));
        tier1.setType(Type.CLIENT);
        Tier tier2 = tierRepository.save(tier1);
        TierDTO tierDTO = modelMapper.map(tier2 , TierDTO.class);
        return tierDTO;
    }

    @Override
    public List<TierDTO> getAllTierFounisseur() {
        List<Tier> tiers = tierRepository.findAllByType(Type.FOURNISSEUR);
        List<TierDTO> tierDTOS = new ArrayList<>();
        for (Tier tier : tiers){
            tierDTOS.add(modelMapper.map(tier , TierDTO.class));
        }
        return tierDTOS;
    }

    @Override
    public List<TierDTO> getAllTierClient() {
        List<Tier> tiers = tierRepository.findAllByType(Type.CLIENT);
        List<TierDTO> tierDTOS = new ArrayList<>();
        for (Tier tier : tiers){
            tierDTOS.add(modelMapper.map(tier , TierDTO.class));
        }
        return tierDTOS;
    }

    @Override
    public void deleteClient(int idclient) {
        tierRepository.deleteById(idclient);
    }

    @Override
    public void deleteFounisseur(int idfounisseur) {
        tierRepository.deleteById(idfounisseur);
    }

    @Override
    public TierDTO updateClient(int idClient,String client,MultipartFile photo)throws IOException {
        Tier tier = tierRepository.findById(idClient).orElseThrow();
        Tier tierr = objectMapper.readValue(client , Tier.class );
        tier.setNom(tierr.getNom());
        tier.setEmail(tierr.getEmail());
        tier.setTel(tierr.getTel());
        tier.setDateOfBirthday(tierr.getDateOfBirthday());
        tier.setCountry(tierr.getCountry());
        tier.setCity(tierr.getCity());
        tier.setCodePostal(tierr.getCodePostal());
        tier.setAddress(tierr.getAddress());
        tier.setDescription(tierr.getDescription());
        tier.setPhoto(fileLoaderService.loadFile(photo));

        Tier tier1 = tierRepository.save(tier);
        TierDTO tierDTO = modelMapper.map(tier1 , TierDTO.class);

        return tierDTO;
    }

    @Override
    public TierDTO updateFounisseur(int idFounisseur, String Founisseur, MultipartFile photo) throws IOException {
        Tier tier = tierRepository.findById(idFounisseur).orElseThrow();
        Tier tierr = objectMapper.readValue(Founisseur , Tier.class );

        tier.setNom(tierr.getNom());
        tier.setEmail(tierr.getEmail());
        tier.setTel(tierr.getTel());
        tier.setDateOfBirthday(tierr.getDateOfBirthday());
        tier.setCountry(tierr.getCountry());
        tier.setCity(tierr.getCity());
        tier.setCodePostal(tierr.getCodePostal());
        tier.setAddress(tierr.getAddress());
        tier.setDescription(tierr.getDescription());
        tier.setPhoto(fileLoaderService.loadFile(photo));

        Tier tier1 = tierRepository.save(tier);
        TierDTO tierDTO = modelMapper.map(tier1 , TierDTO.class);

        return tierDTO;
    }

    @Override
    public TierDTO getClient_FournisseurbyID(int id) {
        Tier tier = tierRepository.findById(id).orElseThrow();
        TierDTO tierDTO = modelMapper.map(tier , TierDTO.class);
        return tierDTO;
    }


}
