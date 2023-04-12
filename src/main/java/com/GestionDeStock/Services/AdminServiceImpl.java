package com.GestionDeStock.Services;

import com.GestionDeStock.DTO.AdminDTO;
import com.GestionDeStock.Entity.Admin;
import com.GestionDeStock.Entity.Role;
import com.GestionDeStock.Entity.User;
import com.GestionDeStock.Repository.AdminRepository;
import com.GestionDeStock.Repository.UserRepository;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.io.IOException;
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


        User user = modelMapper.map(admin,User.class);
        user.setRole(Role.ADMIN);


        user.setPassword(passwordEncoder.encode(admin.getEmailVerificationKey()));

       // user.setPassword(passwordEncoder.encode(admin.getPassword()));

        userRepository.save(user);
         adminRepo.save(admin);


            String subject = "Account details for " + admin.getEmail();
            String content = "Dear " + admin.getName() + ",<br><br>" +
                    "Thank you for registering with our system. Your account details are:<br><br>" +
                    "Email: " + admin.getEmail() + "<br>" +
                    "Password: " + admin.getEmailVerificationKey() + "<br><br>" +
                    "Please keep your password safe and secure.<br><br>" +
                    "Regards,<br>" +
                    "Your System";

            mailService.sendEmail(admin.getEmail(), subject, content);


        return admin ;

    }

    @Override
    public List<Admin> getAllAdmin() {
        return   adminRepo.findAll();

    }
}
