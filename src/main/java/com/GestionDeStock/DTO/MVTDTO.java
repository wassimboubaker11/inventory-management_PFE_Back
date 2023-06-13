package com.GestionDeStock.DTO;


import com.GestionDeStock.Entity.Commande;
import com.GestionDeStock.Entity.Type1;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MVTDTO {

    private int idmvt;

    private int quantity;

    private Type1 type1;

    private ArticleDTO articleDTO;

    private CommandeDTO commandeDTO;
}
