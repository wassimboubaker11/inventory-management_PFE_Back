package com.GestionDeStock.DTO;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TierDTO {


    private int idtier;

    private String nom;


    private String email;


    private String tel;


    private String dateOfBirthday;


    private String country;


    private String city;


    private String codePostal;


    private String address;


    private String photo;


    private String description;
}
