package com.GestionDeStock.Services;

import com.GestionDeStock.DTO.AdminDTO;
import com.GestionDeStock.Entity.Admin;
import com.GestionDeStock.Entity.Role;
import com.GestionDeStock.Entity.User;
import com.GestionDeStock.Repository.AdminRepository;
import com.GestionDeStock.Repository.UserRepository;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService{
    private ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    @Autowired  private FileLoaderService fileLoaderService;

    private ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private  PasswordEncoder passwordEncoder;

    @Autowired
    private AdminRepository adminRepo;

    @Autowired
    private UserRepository userRepository;



    @Autowired
    private  MailService mailService ;




    @Override
    public Admin registerPartner(String DTO , MultipartFile logo) throws IOException, MessagingException, jakarta.mail.MessagingException {

        AdminDTO adminDTO = objectMapper.readValue(DTO, AdminDTO.class);

        adminDTO.setLogo(fileLoaderService.loadFile(logo));

        Admin admin = modelMapper.map(adminDTO,Admin.class);


            adminRepo.save(admin);

            //   String subject = "Account details for " + admin.getEmail();
            //    String content = "Dear " + admin.getName() + ",<br><br>" +
            //          "Thank you for registering with our system. Your account details are:<br><br>" +
            //          "Email: " + admin.getEmail() + "<br>" +
            //          "Password: " + admin.getEmailVerificationKey() + "<br><br>" +
            //          "Please keep your password safe and secure.<br><br>" +
            //         "Regards,<br>" +
            //        "Your System";

            //  mailService.sendEmail(admin.getEmail(), subject, content);

            return admin;



    }


    @Override
    public List<AdminDTO> getAllAdmin() {

        List<Admin> adminList = adminRepo.findAll();
      List<AdminDTO> adminDTOS = new ArrayList<>();
        for (Admin admin : adminList) {
            adminDTOS.add(modelMapper.map(admin, AdminDTO.class));
        }
        return adminDTOS;
    }



    @Override
    public AdminDTO getadminbyid(int idadmin) {
        Admin admin = adminRepo.findById(idadmin).orElseThrow();
        AdminDTO adminDTO = modelMapper.map(admin , AdminDTO.class);
        return adminDTO;
    }
    @Transactional
    @Override
    public Admin valideadmin(int idadmin , Admin adminn ) throws MessagingException, jakarta.mail.MessagingException, IOException {
        Admin admin = adminRepo.findById(idadmin).orElseThrow();
        admin.setValide(adminn.isValide());

        if (admin.isValide() ) {

             User user= new User();
             user.setEmail(admin.getEmail());
             user.setName(admin.getName());
             user.setRole(Role.ADMIN);
             user.setPassword(passwordEncoder.encode(admin.getEmailVerificationKey()));


            String subject = "Account details for " + admin.getEmail();
            String content = "Dear " + admin.getName() + ",<br><br>" +
                    "Thank you for registering with our system. Your account details are:<br><br>" +
                    "Email: " + admin.getEmail() + "<br>" +
                    "Password: " + admin.getEmailVerificationKey() + "<br><br>" +
                    "Please keep your password safe and secure.<br><br>" +
                    "Regards,<br>" +
                    "Your System";
            mailService.sendEmail(admin.getEmail(), subject, content);

            userRepository.save(user);

        } else {
             userRepository.deleteByEmail(admin.getEmail());
        }
        return admin;
    }

    @Transactional
    @Override
    public void deleteadmin(int idadmin) {
        Admin admin = adminRepo.findById(idadmin).orElseThrow();
        userRepository.deleteByEmail(admin.getEmail());
         adminRepo.deleteById(idadmin);


    }

    @Override
    public Admin getadminbyemail(String email) {
        Admin admin = adminRepo.getAdminByEmail(email);

        return admin;
    }


}
