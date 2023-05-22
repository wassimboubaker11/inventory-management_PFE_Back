package com.GestionDeStock.Services;


import com.GestionDeStock.DTO.DepotDTO;
import com.GestionDeStock.DTO.OptionDTO;
import com.GestionDeStock.Entity.Depot;
import com.GestionDeStock.Entity.Option;
import com.GestionDeStock.Repository.OptionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OptionServiceImpl implements OptionService {

    private ModelMapper modelMapper = new ModelMapper();

    @Autowired
    OptionRepository optionRepository;

    @Override
    public OptionDTO saveoption(Option o) {
        Option option = optionRepository.save(o);
        OptionDTO optionDTO = modelMapper.map(option , OptionDTO.class);
        return optionDTO;
    }

    @Override
    public List<OptionDTO> getAlloption() {
        List<Option> options = optionRepository.findAll();
        List<OptionDTO> OptionList = new ArrayList<>();
        for (Option option : options){
            OptionList.add(modelMapper.map(option , OptionDTO.class));
        }
        return OptionList;
    }

    @Override
    public void deleteoption(int idoption) {
        optionRepository.deleteById(idoption);
    }
}
