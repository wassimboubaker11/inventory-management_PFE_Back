package com.GestionDeStock.auth;

import com.GestionDeStock.DTO.AdminDTO;
import com.GestionDeStock.Entity.Role;
import com.GestionDeStock.Entity.Super_Admin;
import com.GestionDeStock.Entity.User;
import com.GestionDeStock.Repository.SuperAdminRepository;
import com.GestionDeStock.Repository.UserRepository;
import com.GestionDeStock.config.JwtService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private ModelMapper modelMapper = new ModelMapper();

    private final UserRepository repository;
    private final SuperAdminRepository superAdminRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;
    public AuthenticationResponse register(RegisterRequest request) {
         var user = User.builder()
                 .name(request.getName())
                 .email(request.getEmail())
                 .password(passwordEncoder.encode(request.getPassword()))
                 .role(Role.SUPER_ADMIN)
                 .build();
         repository.save(user);
         Super_Admin super_admin = modelMapper.map(user,Super_Admin.class);
         superAdminRepository.save(super_admin);

         var claims = new HashMap<String , Object>();
         claims.put("Role",user.getRole());
         claims.put("username" , user.getName());



        var jwtToken = jwtService.generateToken(claims, user);
       // var claimss = jwtService.extra(jwtToken);
         return AuthenticationResponse.builder()
                 .token(jwtToken)
                 .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();

        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
