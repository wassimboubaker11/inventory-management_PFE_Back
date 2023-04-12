package com.GestionDeStock.Services;

import com.GestionDeStock.Entity.Admin;
import com.GestionDeStock.Entity.Gestionaire;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface GestionaireService {

    Gestionaire registerGestionaire(String DTO, MultipartFile photo) throws IOException;
}
