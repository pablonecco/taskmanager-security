package com.example.taskmanager.controllers;

import com.example.taskmanager.controllers.request.CreateUserDTO;
import com.example.taskmanager.entities.RoleEntity;
import com.example.taskmanager.entities.UserEntity;
import com.example.taskmanager.repositories.IRoleRepository;
import com.example.taskmanager.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/test")
public class TestController {

    //User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    @Autowired
    private IRoleRepository roleRepository;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/hellonotsecured")
    public String helloNotSecured () {
        return "Hello World Not Secured";
    }

    @GetMapping("/hellouser")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String helloUser () {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println("ID ES IGUAL A: " + userRepository.findByUsername(user.getUsername()).getId());
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return "Hello World For Users"; }

    @GetMapping("/helloadmin")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String helloAdmin () { return "Hello World For Admins"; }


    @GetMapping("/hellosecured2")
    public String anotherHelloSecured () { return "Another Hello World Secured"; }

    @PostMapping("/createuser")
    public String createUser (@Valid @RequestBody CreateUserDTO createUserDTO) {

        Set<RoleEntity> roles = new HashSet<>();
        //ID 1 crea con rol ROLE_USER
        //ID 2 crea con rol ROLE_ADMIN
        roles.add(roleRepository.findById(1));

        UserEntity userEntity = UserEntity.builder()
                .username(createUserDTO.getUsername())
                .password(passwordEncoder.encode(createUserDTO.getPassword()))
                //.password("1234")
                .roles(roles)
                .build();

        userRepository.save(userEntity);
        return "Se ha creado con éxito";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/deleteuser")
    public String deleteUser (@RequestParam int id) {
        userRepository.deleteById(id);
        return "Usuario borrado con exito!";
    }
}
