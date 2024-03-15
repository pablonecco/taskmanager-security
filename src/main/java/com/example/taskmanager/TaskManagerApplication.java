package com.example.taskmanager;

import com.example.taskmanager.controllers.request.CreateUserDTO;
import com.example.taskmanager.entities.RoleEntity;
import com.example.taskmanager.entities.UserEntity;
import com.example.taskmanager.repositories.IRoleRepository;
import com.example.taskmanager.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class TaskManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskManagerApplication.class, args);
	}

	/*@Autowired
	IRoleRepository roleRepository;
	@Autowired
	IUserRepository userRepository;
	@Autowired
	PasswordEncoder passwordEncoder;
	@Bean
	public String createUser () {

		Set<RoleEntity> roles = new HashSet<>();
		//ID 1 por defecto crea con rol ROLE_USER
		//ID 2 por defecto crea con rol ROLE_ADMIN
		roles.add(roleRepository.findById(2));

		UserEntity userEntity = UserEntity.builder()
				.username("admin")
				.password(passwordEncoder.encode("admin"))
				//.password("1234")
				.roles(roles)
				.build();

		userRepository.save(userEntity);
		return "Se ha creado con éxito";
	} */
}
