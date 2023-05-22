package com.GestionDeStock.Entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "Option")
public class Option implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idoption")
    private int idoption;

    @Column(name = "Nom")
    private String nom;



    @JsonIgnore
    @OneToMany(mappedBy = "option")
    private List<SousOption> sous_options;
}
