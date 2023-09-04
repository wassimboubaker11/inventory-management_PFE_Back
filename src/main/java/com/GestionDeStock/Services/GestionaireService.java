package com.GestionDeStock.Services;

import com.GestionDeStock.DTO.GestionaireDTO;
import com.GestionDeStock.Entity.Admin;
import com.GestionDeStock.Entity.Gestionaire;
import com.GestionDeStock.Entity.User;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

public interface GestionaireService {

    GestionaireDTO registerGestionaire(String DTO, int idadmin , MultipartFile photo ) throws IOException;

    Gestionaire validegestionaire(int idg , Gestionaire gestionaire) throws MessagingException, jakarta.mail.MessagingException, IOException;

    List<GestionaireDTO> getGestionairesByAdminId(Integer adminId);

    GestionaireDTO getgestionairebyemail(String email);

    void deletegestionairebyid(int idgestionaire);

    GestionaireDTO  getGestionaireById(int idGestionaire);
}
