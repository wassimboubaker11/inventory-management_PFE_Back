package com.GestionDeStock.Services;

import com.GestionDeStock.DTO.SousOptionDTO;
import com.GestionDeStock.Entity.SousOption;

import java.util.List;

public interface Sous_OptionService {

        SousOptionDTO saveSousOption(SousOption sous_option , int idoption);

        List<SousOptionDTO> getAllSousOption();
}
