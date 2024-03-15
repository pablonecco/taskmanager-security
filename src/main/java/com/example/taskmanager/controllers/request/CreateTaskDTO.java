package com.example.taskmanager.controllers.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateTaskDTO {
    private String titulo;
    private String nombre;
    private String descripcion;
    private boolean finalizada;
    private boolean enCurso;
}
