package com.GestionDeStock.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(	name = "gestionaire",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "email")
        })
public class Gestionaire  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "Name")
    private String name;

    @Column(name = "Email")
    private String email;

    @Column(name = "CIN")
    private String cin;

    @Column(name = "Tel")
    private int tel;

    @Column(name = "Country")
    private String country;


    @Column(name = "City")
    private String city;

    @Column(name = "CodePostal")
    private String codePostal;

    @Column(name = "address")
    private String address;


    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "Birthday")
    private String dateOfBirthday;


    @Column(name = "Description")
    private String description;


    @Column(name = "photo")
    private String photo;

    @Column(name = "EMAIL_VERIFICATION_KEY")
    private String emailVerificationKey= UUID.randomUUID().toString().replace("-", "");

    @Column(name = "VALIDE")
    private boolean valide =false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="admin_id")
    @JsonBackReference
    @JsonIgnore
    private Admin admin;

}
