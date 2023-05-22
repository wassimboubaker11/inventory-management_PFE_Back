package com.GestionDeStock.Entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.List;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Tier", uniqueConstraints = {@UniqueConstraint(columnNames = "email")})

public class Tier implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idtier")
    private int idtier;

    @Column(name = "COMPANY_NAME")
    private String nom;

    @Column(name = "Email")
    private String email;

    @Column(name = "tel")
    private String tel;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "Birthday")
    private String dateOfBirthday;

    @Column(name = "Country")
    private String country;

    @Column(name = "City")
    private String city;

    @Column(name = "CodePostal")
    private String codePostal;

    @Column(name = "address")
    private String address;

    @Column(name = "photo")
    private String photo;

    @Column(name = "Description")
    private String description;

    @Enumerated(EnumType.STRING)
    private Type type;


    @OneToMany(mappedBy ="tier")
    private List<Commande> commandes;
}
