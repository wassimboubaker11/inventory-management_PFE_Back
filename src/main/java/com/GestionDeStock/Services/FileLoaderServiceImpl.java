package com.GestionDeStock.Services;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileLoaderServiceImpl implements FileLoaderService {
    @Override
    public String loadFile(MultipartFile fileMultipart) throws IOException {
        String filePath = StringUtils.cleanPath(fileMultipart.getOriginalFilename());
        Path fileStoragepreuve = Paths.get(uploadPath, filePath).toAbsolutePath().normalize();
        if (Files.exists(fileStoragepreuve)) {
            String fileName = filePath;
            String fileExtension = "";
            int dotIndex = filePath.lastIndexOf(".");
            if (dotIndex != -1) {
                fileName = filePath.substring(0, dotIndex);
                fileExtension = filePath.substring(dotIndex);
            }
            int i = 1;
            while (Files.exists(fileStoragepreuve)) {
                filePath = fileName + "-" + i + fileExtension;
                fileStoragepreuve = Paths.get(uploadPath, filePath).toAbsolutePath().normalize();
                i++;
            }
        }
        Files.copy(fileMultipart.getInputStream(), fileStoragepreuve);
        return filePath;
    }
    public ResponseEntity<Resource> getImageByFileName(String fileName) throws IOException {
        // Récupérer le chemin du fichier
        Path filePath = Paths.get(uploadPath).resolve(fileName).normalize();
        // Vérifier si le fichier existe
        if (Files.exists(filePath) && Files.isReadable(filePath)) {
            // Renvoyer la ressource en utilisant un MediaType approprié
            Resource resource = new UrlResource(filePath.toUri());
            MediaType mediaType = MediaTypeFactory.getMediaType(resource).orElse(MediaType.APPLICATION_OCTET_STREAM);
            return ResponseEntity.ok().contentType(mediaType).body(resource);
        } else {
            // Renvoyer une réponse HTTP 404 si le fichier n'existe pas ou n'est pas lisible
            return ResponseEntity.notFound().build();
        }
    }
    public ResponseEntity<String> deleteFileByFileName(String fileName) {
        // Récupérer le chemin du fichier
        Path filePath = Paths.get(uploadPath).resolve(fileName).normalize();
        // Vérifier si le fichier existe
        if (Files.exists(filePath) && Files.isReadable(filePath)) {
            // Supprimer le fichier
            try {
                Files.delete(filePath);
                return ResponseEntity.ok("Le fichier a été supprimé avec succès.");
            } catch (IOException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Une erreur s'est produite lors de la suppression du fichier.");
            }
        } else {
            // Renvoyer une réponse HTTP 404 si le fichier n'existe pas ou n'est pas lisible
            return ResponseEntity.notFound().build();
        }
    }
}
