package com.GestionDeStock.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Depot")
public class Depot implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "iddepot")
    private int iddepot;

    @Column(name = "nom")
    private String nom;

    @Column(name = "adresse")
    private String adresse;

    @Column(name = "numero")
    private int numero;

    @Column(name = "datecreation")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private String datecreation;


    @ManyToOne
    private Admin admin;


    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.REMOVE}, fetch = FetchType.LAZY )
    @JoinTable(
            name = "depot_article",
            joinColumns = @JoinColumn(name = "depot_id"  ),
            inverseJoinColumns = @JoinColumn(name = "article_id" ))
    private Set<Article> article = new HashSet<>();

}
