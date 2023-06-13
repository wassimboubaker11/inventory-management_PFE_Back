package com.GestionDeStock.Services;

import com.GestionDeStock.DTO.MVTDTO;

import java.util.List;

public interface MVTService {

    List<MVTDTO> getAllMVTbyIdCommande(int idcommande);

    List<MVTDTO> getAllMVT();
}
