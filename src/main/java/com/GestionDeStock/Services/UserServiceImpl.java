package com.GestionDeStock.Services;

import com.GestionDeStock.DTO.UserDTO;
import com.GestionDeStock.Entity.Role;
import com.GestionDeStock.Entity.User;
import com.GestionDeStock.Repository.UserRepository;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class UserServiceImpl implements UserService{



    private ModelMapper modelMapper = new ModelMapper();
    @Autowired
    private UserRepository userRepository;





    @Override
    public UserDTO getuser(String email, Role role) {
        User user = userRepository.getUserByEmailAndRole(email , role);
        UserDTO userDTO = modelMapper.map(user , UserDTO.class);
        return userDTO;
    }
}
