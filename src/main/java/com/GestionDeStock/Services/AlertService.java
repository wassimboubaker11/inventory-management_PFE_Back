package com.GestionDeStock.Services;

import com.GestionDeStock.DTO.AlertDTO;
import com.GestionDeStock.Entity.Alert;

import java.util.List;

public interface AlertService {

    AlertDTO addalert(Alert alert,int idarticle);

    List<AlertDTO> gettallalert();

    void deleteAlert(int idalert);
}
