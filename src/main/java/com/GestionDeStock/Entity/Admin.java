package com.GestionDeStock.Entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(	name = "ADMIN",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "email")
        })
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "COMPANY_NAME")
    private String name;

    @Column(name = "Email")
    private String email;

    @Column(name = "tel")
    private String tel;


    @Column(name = "WEB_SITE")
    private String site;

    @Column(name = "LOGO")
    private String logo;

    @Column (name="Location")
    private String adresse;


   // @Column(name = "Enabled")
   // private Boolean enabled=true;

    @Column(name = "EMAIL_VERIFICATION_KEY")
    private String emailVerificationKey =UUID.randomUUID().toString().replace("-", "");

    @Column(name = "VALIDE")
    private boolean valide =false;

    @JsonIgnore
    @OneToMany(mappedBy="admin",cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Gestionaire> GestonnaireList = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "admin")
    private List<Depot> depots;
}
