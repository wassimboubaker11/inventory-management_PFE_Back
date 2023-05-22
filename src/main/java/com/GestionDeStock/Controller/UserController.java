package com.GestionDeStock.Controller;


import com.GestionDeStock.DTO.UserDTO;
import com.GestionDeStock.Entity.Role;
import com.GestionDeStock.Entity.User;
import com.GestionDeStock.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    private UserService userService;

    //  http://localhost:8081/api/v1/getuserbyemailandrole/{email}/{role}

    @GetMapping("/getuserbyemailandrole/{email}/{role}")
    public UserDTO getuserbyemailandrole(@PathVariable ("email") String email , @PathVariable Role role){
        return userService.getuser(email , role);
    }
}
