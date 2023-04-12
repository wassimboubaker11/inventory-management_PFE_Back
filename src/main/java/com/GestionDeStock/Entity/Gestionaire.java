package com.GestionDeStock.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(	name = "gestionaire",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "email")
        })
public class Gestionaire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "Name")
    private String name;

    @Column(name = "CIN")
    private String cin;

    @Column(name = "Ville")
    private String ville;

    @Column(name = "CodePostal")
    private String codePostal;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "Birthday")
    private String dateOfBirthday;



    @Column(name = "Description")
    private String description;

    @Column(name = "Email")
    private String email;

    @Column(name = "photo")
    private String photo;

    @Column(name = "Enabled")
    private Boolean enabled=true;

    @Column(name = "EMAIL_VERIFICATION_KEY")
    private String emailVerificationKey= UUID.randomUUID().toString().replace("-", "");

    @Column(name = "EMAIL_VERIFIED")
    private boolean emailVerified;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="admin_id")
    @JsonBackReference
    @JsonIgnore
    private Admin admin;

}
