package com.GestionDeStock.Controller;

import com.GestionDeStock.Entity.Admin;
import com.GestionDeStock.Services.AdminService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;


@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @RequestMapping(value = "/register", method = RequestMethod.POST,consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Admin> registerPartner(@RequestPart("admin") String DTO,
                                                 @RequestPart("logo") MultipartFile logo) throws IOException, MessagingException, jakarta.mail.MessagingException {

        return ResponseEntity.ok(adminService.registerPartner(DTO,logo));
    }

    @GetMapping("/getAllAdmin")
    public List<Admin> getAllAdmin(){
        return  adminService.getAllAdmin();

    }
}
