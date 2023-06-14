package com.GestionDeStock.Services;

import com.GestionDeStock.DTO.OptionDTO;
import com.GestionDeStock.DTO.SousOptionDTO;
import com.GestionDeStock.Entity.SousOption;

import java.util.List;

public interface Sous_OptionService {

        SousOptionDTO saveSousOption(SousOption sous_option , int idoption);

        List<SousOptionDTO> getAllSousOption();

        List<SousOptionDTO> getSousOptionsbyidoption(int idoption);

        void deletesous_option(int idsous_option);
}
