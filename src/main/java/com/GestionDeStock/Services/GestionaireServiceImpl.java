package com.GestionDeStock.Services;

import com.GestionDeStock.DTO.AdminDTO;
import com.GestionDeStock.DTO.GestionaireDTO;
import com.GestionDeStock.Entity.Admin;
import com.GestionDeStock.Entity.Gestionaire;
import com.GestionDeStock.Entity.Role;
import com.GestionDeStock.Entity.User;
import com.GestionDeStock.Repository.GestionaireRepository;
import com.GestionDeStock.Repository.UserRepository;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class GestionaireServiceImpl implements  GestionaireService{
    private ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    @Autowired  private FileLoaderService fileLoaderService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private ModelMapper modelMapper = new ModelMapper();

    @Autowired
    GestionaireRepository gestionaireRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Gestionaire registerGestionaire(String DTO, MultipartFile photo) throws IOException {

        GestionaireDTO gestionaireDTO = objectMapper.readValue(DTO, GestionaireDTO.class);

        gestionaireDTO.setPhoto(fileLoaderService.loadFile(photo));

        Gestionaire gestionaire = modelMapper.map(gestionaireDTO,Gestionaire.class);


        User user = modelMapper.map(gestionaire,User.class);
        user.setRole(Role.USER);

        user.setPassword(passwordEncoder.encode(gestionaire.getEmailVerificationKey()));

        userRepository.save(user);
        return gestionaireRepository.save(gestionaire);
    }
}
