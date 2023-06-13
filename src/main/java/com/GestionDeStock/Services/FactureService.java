package com.GestionDeStock.Services;

import com.GestionDeStock.DTO.FactureDTO;
import com.GestionDeStock.Entity.Facture;

public interface FactureService {

    FactureDTO saveFacture(int idcommande);

    FactureDTO getFacturebyidcommande(int idcommande);
}
