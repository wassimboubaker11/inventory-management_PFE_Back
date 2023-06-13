package com.GestionDeStock.DTO;

import com.GestionDeStock.Entity.Alert;
import com.GestionDeStock.Entity.Category;
import com.GestionDeStock.Entity.Depot;
import com.GestionDeStock.Entity.Type2;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Set;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDTO {

    private int idarticle;

    private String nom;

    private String ref_article;

    private String num_serie;

    private String code_barre;

    private String marque;

    private float prixachat;

    private float prixvente;

    private int quantite;

    private String picture;

    private Type2 status;

    private String description;

    private CategoryDTO category;

    private Set<DepotDTO> depot;


}
