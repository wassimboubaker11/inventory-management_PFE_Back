package com.GestionDeStock.Services;


import com.GestionDeStock.DTO.OptionDTO;
import com.GestionDeStock.Entity.Option;

import java.util.List;

public interface OptionService {

    OptionDTO saveoption(Option o);

    List<OptionDTO> getAlloption();

    void deleteoption(int idoption);
}
