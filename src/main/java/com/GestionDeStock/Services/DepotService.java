package com.GestionDeStock.Services;

import com.GestionDeStock.DTO.DepotDTO;
import com.GestionDeStock.Entity.Depot;

import java.util.List;

public interface DepotService {


    DepotDTO savedepot(Depot d);

    void deletedepot(int iddepot);

    DepotDTO updatedepot(Depot d , int iddepot);

    List<DepotDTO> getalldepot();

    DepotDTO getdepotbyid(int iddepot);
}
