package com.GestionDeStock.Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "MVT")
public class MVT implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idmvt")
    private int idmvt;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "Nomvariant")
    private String nomVariant;


    @Enumerated(EnumType.STRING)
    private Type1 type1;



    @ManyToOne
    private Commande commande;

    @ManyToOne
    private Article article;
}

