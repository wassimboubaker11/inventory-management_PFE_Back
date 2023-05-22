package com.GestionDeStock.Services;

import com.GestionDeStock.DTO.UserDTO;
import com.GestionDeStock.Entity.Admin;
import com.GestionDeStock.Entity.Role;
import com.GestionDeStock.Entity.User;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.io.IOException;

public interface UserService {




    UserDTO getuser(String email , Role role);
}
