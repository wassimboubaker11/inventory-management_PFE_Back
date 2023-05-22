package com.GestionDeStock.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OptionDTO {

    private int idoption;

    private String nom;

    private List<SousOptionDTO> sous_options;
}
