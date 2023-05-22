package com.GestionDeStock.Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
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



    @Column(name = "quantite")
    private int quantite;

    @Column(name = "montant")
    private Float montant;

    @ManyToOne
    private Tier tier;

    @OneToMany(mappedBy = "commande")
    private List<MVT> mvtList;
}

