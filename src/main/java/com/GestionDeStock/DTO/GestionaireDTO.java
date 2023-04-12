package com.GestionDeStock.DTO;


import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@AllArgsConstructor
public class GestionaireDTO {

    private String name;


    private String cin;


    private String ville;


    private String codePostal;


    private String dateOfBirthday;


    private String photo;


    private String description;


    private String email;


    private String password;

    public GestionaireDTO(){

    }
}
