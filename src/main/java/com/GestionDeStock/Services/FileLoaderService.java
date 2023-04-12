package com.GestionDeStock.Services;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileLoaderService {
    String uploadPath = System.getProperty("user.home") + "/Desktop/PFE_ap/GestionDeStock/uploads/";

    String loadFile(MultipartFile preuvereg) throws IOException;

    ResponseEntity<Resource> getImageByFileName(String fileName) throws IOException;

    ResponseEntity<String> deleteFileByFileName(String fileName);
}
