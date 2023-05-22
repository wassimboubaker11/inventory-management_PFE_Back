package com.GestionDeStock.DTO;

import com.GestionDeStock.Entity.Option;

import com.GestionDeStock.Entity.Variant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SousOptionDTO {

    private int idsousoption;

    private String nom;

    private Option option;

   // private List<Variant> variants;


}
