package com.GestionDeStock.DTO;

import com.GestionDeStock.Entity.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DepotDTO {

    private int iddepot;

    private String nom;

    private String adresse;

    private int numero;

    private String datecreation;

    // private List<ArticleDTO> article;


}
