package com.GestionDeStock.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="Article")
public class Article implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idarticle" )
    private int idarticle;

    @Column(name = "nom")
    private String nom;

    @Column(name = "ref_article")
    private String ref_article;

    @Column(name = "num_serie")
    private String num_serie;

    @Column(name = "codebare")
    private String code_barre;

    @Column(name = "marque")
    private String marque;

    @Column(name = "prixachat")
    private float prixachat;

    @Column(name = "prixvente")
    private float prixvente;

    @Column(name = "quantite")
    private int quantite;




    @Column(name = "description")
    private String description;

    @Column(name = "PICTURE")
    private String picture;

    @Column(name = "status")
    private boolean status;

    @JsonIgnore
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.LAZY )
    @JoinTable(
            name = "depot_article",
            joinColumns = @JoinColumn(name = "article_id"  ),
            inverseJoinColumns = @JoinColumn(name = "depot_id" ))
    private Set<Depot> depot = new HashSet<>();

    @JsonIgnore
    @JoinColumn(name="category_id")
    @ManyToOne
    private Category category;

    @JsonIgnore
    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    private List<Variant> variants;

    @OneToMany(mappedBy = "article")
    private List<MVT> mvtList;
}
