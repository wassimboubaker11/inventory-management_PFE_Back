package com.GestionDeStock.Entity;


import jakarta.persistence.*;
import lombok.*;


import java.io.Serializable;

import java.time.LocalDateTime;

import java.util.List;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Commande")
public class Commande implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idcommande")
    private int idcommande;

    @Column(name = "nom")
    private String nom;

    @Column(name = "quantite")
    private int quantite;

    @Column(name = "montant")
    private Float montant;


    @Column(name = "Date")
    private LocalDateTime date;

    @ManyToOne
    private Tier tier;

    @OneToMany(mappedBy = "commande")
    private List<MVT> mvtList;

    @OneToOne
    private Facture facture;
}

