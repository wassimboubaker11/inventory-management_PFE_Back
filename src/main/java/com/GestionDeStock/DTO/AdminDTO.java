package com.GestionDeStock.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class AdminDTO {


    private String name;
    private String email;
    private String tel;
    private String adresse;
    private String site;
    private String description;
    private String logo;

    public AdminDTO() {
    }
}

