package com.GestionDeStock.Services;

import com.GestionDeStock.DTO.OptionDTO;
import com.GestionDeStock.DTO.SousOptionDTO;
import com.GestionDeStock.Entity.Option;
import com.GestionDeStock.Entity.SousOption;
import com.GestionDeStock.Repository.OptionRepository;
import com.GestionDeStock.Repository.Sous_OptionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Sous_OptionServiceImpl implements Sous_OptionService{

    @Autowired
    private Sous_OptionRepository sous_optionRepository;

    @Autowired
    private OptionRepository  optionRepository;

    private ModelMapper modelMapper = new ModelMapper();


    @Override
    public SousOptionDTO saveSousOption(SousOption sous_option, int idoption) {
        Option option = optionRepository.findById(idoption).get();



        SousOption sous_option1 = sous_optionRepository.save(sous_option);
        sous_option1.setOption(option);
        sous_optionRepository.save(sous_option1);


        SousOptionDTO sousOptionDTO = modelMapper.map(sous_option1 , SousOptionDTO.class);
        return sousOptionDTO;


    }

    @Override
    public List<SousOptionDTO> getAllSousOption() {
        List<SousOption> sousOptions = sous_optionRepository.findAll();
        List<SousOptionDTO> sousOptionDTOS = new ArrayList<>();
        for(SousOption sousOption : sousOptions){
            sousOptionDTOS.add(modelMapper.map(sousOption , SousOptionDTO.class));
        }
        return sousOptionDTOS;
    }


}
