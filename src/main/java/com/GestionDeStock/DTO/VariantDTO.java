package com.GestionDeStock.DTO;


import com.GestionDeStock.Entity.SousOption;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VariantDTO {

    private int idvariant;

    private String nom;

    private int quantity;

    private List<SousOptionDTO> sousOptions;
}
