package com.Kipper.First_spring_app.controller;

import com.Kipper.First_spring_app.domain.user.AuthenticationDIO;
import com.Kipper.First_spring_app.domain.user.User;
import com.Kipper.First_spring_app.domain.user.UserRequestDTO;
import com.Kipper.First_spring_app.domain.user.UserResponseDTO;
import com.Kipper.First_spring_app.repository.UserRepository;
import com.Kipper.First_spring_app.sevice.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class UserController {
    @Autowired
    TokenService tokenService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository repository;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDIO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token = tokenService.createToken((User) auth.getPrincipal());
        return ResponseEntity.ok(new UserResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid UserRequestDTO data){
        if (this.repository.findByLogin(data.login()) != null){
            return ResponseEntity.badRequest().build();
        }
        String encryptedPasswold = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.login(), encryptedPasswold, data.userRole());

        this.repository.save(newUser);
        return ResponseEntity.ok().build();
    }
}
