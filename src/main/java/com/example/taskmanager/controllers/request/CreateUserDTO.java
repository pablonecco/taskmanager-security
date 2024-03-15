package com.example.taskmanager.controllers.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class CreateUserDTO {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
