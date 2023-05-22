package com.GestionDeStock.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Vairant")
public class Variant implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idvariant")
    private int idvariant;

    @JsonIgnore
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.LAZY )
    @JoinTable(
            name = "variant_SousOption",
            joinColumns = @JoinColumn(name = "Variant_id"  ),
            inverseJoinColumns = @JoinColumn(name = "SousOption_id" ))
    private List<SousOption> sousOptions ;

    @JsonIgnore
    @ManyToOne
    private Article article;
}


