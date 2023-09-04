package com.GestionDeStock.Services;

import com.GestionDeStock.DTO.AdminDTO;
import com.GestionDeStock.DTO.GestionaireDTO;
import com.GestionDeStock.Entity.Admin;
import com.GestionDeStock.Entity.Gestionaire;
import com.GestionDeStock.Entity.Role;
import com.GestionDeStock.Entity.User;
import com.GestionDeStock.Repository.AdminRepository;
import com.GestionDeStock.Repository.GestionaireRepository;
import com.GestionDeStock.Repository.UserRepository;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GestionaireServiceImpl implements  GestionaireService{
    private ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    @Autowired  private FileLoaderService fileLoaderService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private GestionaireRepository gestionaireRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private  MailService mailService ;

    @Override
    public GestionaireDTO registerGestionaire(String DTO , int idadmin,  MultipartFile photo) throws IOException {

        Admin admin = adminRepository.findById(idadmin).orElseThrow();


        Gestionaire gestionaire = objectMapper.readValue(DTO, Gestionaire.class);

        gestionaire.setPhoto(fileLoaderService.loadFile(photo));
        gestionaire.setAdmin(admin);

         Gestionaire gestionaire1 =gestionaireRepository.save(gestionaire);

        GestionaireDTO gestionaireDTO = modelMapper.map(gestionaire1,GestionaireDTO.class);



        //User user = modelMapper.map(gestionaire,User.class);
       // user.setRole(Role.USER);

      //  user.setPassword(passwordEncoder.encode(gestionaire.getEmailVerificationKey()));

       // userRepository.save(user);

        return gestionaireDTO;
    }
    @Transactional
    @Override
    public Gestionaire validegestionaire(int idg, Gestionaire gestionaire) throws MessagingException, jakarta.mail.MessagingException, IOException {
        Gestionaire gestionaire1 = gestionaireRepository.findById(idg).orElseThrow();
        gestionaire1.setValide(gestionaire.isValide());
        if(gestionaire1.isValide() ){

            User user = new User();
            user.setName(gestionaire1.getName());
            user.setEmail(gestionaire1.getEmail());
            user.setRole(Role.USER);
            user.setPassword(passwordEncoder.encode(gestionaire1.getEmailVerificationKey()));


            String subject = "Account details for " + gestionaire1.getEmail();
            String content = "Dear " + gestionaire1.getName() + ",<br><br>" +
                    "Thank you for registering with our system. Your account details are:<br><br>" +
                    "Email: " + gestionaire1.getEmail() + "<br>" +
                    "Password: " + gestionaire1.getEmailVerificationKey() + "<br><br>" +
                    "Please keep your password safe and secure.<br><br>" +
                    "Regards,<br>" +
                    "Your System";
            mailService.sendEmail(gestionaire1.getEmail(), subject, content);

            gestionaireRepository.save(gestionaire1);
            userRepository.save(user);

        }else {
            userRepository.deleteByEmail(gestionaire1.getEmail());
        }
        return gestionaire1;
    }


    @Override
    public List<GestionaireDTO> getGestionairesByAdminId(Integer adminId) {
        List<Gestionaire> gestionaires = gestionaireRepository.findAllByAdminId(adminId);
        return gestionaires.stream()
                .map(g -> modelMapper.map(g, GestionaireDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public GestionaireDTO getgestionairebyemail(String email) {
        Gestionaire gestionaire = gestionaireRepository.getGestionaireByEmail(email);
        GestionaireDTO gestionaireDTO = modelMapper.map(gestionaire , GestionaireDTO.class);
        return gestionaireDTO;
    }

    @Override
    public void deletegestionairebyid(int idgestionaire) {
         gestionaireRepository.deleteById(idgestionaire);
    }

    @Override
    public GestionaireDTO getGestionaireById(int idGestionaire) {
        Gestionaire gestionaire = gestionaireRepository.findById(idGestionaire).orElse(null);
        GestionaireDTO gestionaireDTO = modelMapper.map(gestionaire , GestionaireDTO.class);
        return gestionaireDTO;
    }
}
