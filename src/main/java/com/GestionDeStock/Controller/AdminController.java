package com.GestionDeStock.Controller;

import com.GestionDeStock.DTO.AdminDTO;
import com.GestionDeStock.Entity.Admin;
import com.GestionDeStock.Services.AdminService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

@CrossOrigin("*")
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
    public List<AdminDTO> getAllAdmin(){
        return  adminService.getAllAdmin();

    }

    // http://localhost:8081/api/v1/admin/edit/1
    @PutMapping("/edit/{idadmin}")
    public Admin editadmin(@PathVariable ("idadmin") int idadmin, @RequestBody Admin adminn) throws MessagingException, jakarta.mail.MessagingException, IOException {
        return adminService.valideadmin(idadmin,  adminn );
    }

   // http://localhost:8081/api/v1/admin/getadminbyid/1
    @GetMapping("/getadminbyid/{idadmin}")
    public AdminDTO getadminbyid(@PathVariable ("idadmin") int idadmin){
        return  adminService.getadminbyid(idadmin);

    }
    // http://localhost:8081/api/v1/admin/deleteadmin/8
    @DeleteMapping("/deleteadmin/{idadmin}")
    public void deleteadmin(@PathVariable int idadmin){
         adminService.deleteadmin(idadmin);
    }


    // http://localhost:8081/api/v1/admin/getadminbyemail/
    @GetMapping("/getadminbyemail/{email}")
    public Admin getadminbyemail (@PathVariable ("email") String email){
        return adminService.getadminbyemail(email);
    }

}
