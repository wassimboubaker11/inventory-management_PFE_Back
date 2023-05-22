package com.GestionDeStock.Services;

import com.GestionDeStock.DTO.AdminDTO;
import com.GestionDeStock.DTO.DepotDTO;
import com.GestionDeStock.Entity.Admin;
import com.GestionDeStock.Entity.Depot;

import com.GestionDeStock.Repository.DepotRepository;
import jakarta.annotation.Resource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DepotServiceImpl implements DepotService{

    @Autowired
     DepotRepository depotRepository;


    private ModelMapper modelMapper = new ModelMapper();


    @Override
    public DepotDTO savedepot(Depot d) {
       Depot depot = depotRepository.save(d);
        DepotDTO depotDTO = modelMapper.map(depot,DepotDTO.class);
        return depotDTO;
    }

    @Override
    public void deletedepot(int iddepot) {
        depotRepository.deleteById(iddepot);
    }

    @Override
    public DepotDTO updatedepot(Depot d, int iddepot) {
        Depot depot = depotRepository.findById(iddepot).orElseThrow();
        depot.setNom(d.getNom());
        depot.setAdresse(d.getAdresse());
        depot.setDatecreation(d.getDatecreation());
        depot.setNumero(d.getNumero());
        Depot depot1 =depotRepository.save(depot);

        DepotDTO depotDTO = modelMapper.map(depot1 , DepotDTO.class);
        return depotDTO;
    }

    @Override
    public List<DepotDTO> getalldepot() {
        List<Depot> depotList = depotRepository.findAll();
        List<DepotDTO> depotDTOList = new ArrayList<>();
        for (Depot depot : depotList) {
            depotDTOList.add(modelMapper.map(depot, DepotDTO.class));
        }
        return depotDTOList;

    }

    @Override
    public DepotDTO getdepotbyid(int iddepot) {
        Depot depot = depotRepository.findById(iddepot).orElseThrow();
        DepotDTO depotDTO = modelMapper.map(depot , DepotDTO.class);
        return depotDTO;
    }
}
