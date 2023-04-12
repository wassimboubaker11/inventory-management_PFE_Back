package com.GestionDeStock.Services;

import com.GestionDeStock.Entity.Admin;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

public interface AdminService {
    Admin registerPartner(String DTO, MultipartFile logo) throws IOException, MessagingException, jakarta.mail.MessagingException;

    List<Admin> getAllAdmin();

}
