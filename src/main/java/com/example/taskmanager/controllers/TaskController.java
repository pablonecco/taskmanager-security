package com.example.taskmanager.controllers;

import com.example.taskmanager.controllers.request.CreateTaskDTO;
import com.example.taskmanager.entities.Task;
import com.example.taskmanager.entities.UserEntity;
import com.example.taskmanager.models.TaskModel;
import com.example.taskmanager.repositories.IUserRepository;
import com.example.taskmanager.services.ITaskService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
public class TaskController {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    @Qualifier("taskService")
    private ITaskService taskService;

    private ModelMapper modelMapper = new ModelMapper();

    @GetMapping("/tasks")
    public List<TaskModel> getAll () {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserEntity userEntity = userRepository.findByUsername(user.getUsername());
        List<Task> tasks = taskService.findByUser(userEntity);
        List<TaskModel> tasksModel = new ArrayList<>();
        for (int i=0; i<tasks.size(); i++) {
            tasksModel.add(modelMapper.map(tasks.get(i), TaskModel.class));
        }
        return tasksModel;
    }

    @GetMapping("/task/{id}")
    public Task findById (@PathVariable("id") int id) {
        return taskService.findById(id);
    }

    @PostMapping("/create")
    public String createTask (@RequestBody CreateTaskDTO createTaskDTO) {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserEntity userEntity = userRepository.findByUsername(user.getUsername());

        Task tarea = Task.builder()
                        .titulo(createTaskDTO.getTitulo())
                        .descripcion(createTaskDTO.getDescripcion())
                        .enCurso(createTaskDTO.isEnCurso())
                        .nombre(createTaskDTO.getNombre())
                        .finalizada(createTaskDTO.isFinalizada())
                        .user(userEntity)
                        .build();


        taskService.insertOrUpdate(tarea);

        return "Tarea creada con exito!";
    }

    @DeleteMapping("/delete/{id}")
    public boolean deleteTask(@PathVariable("id") int id) {
        return taskService.remove(id);
    }

    @PutMapping("/finalizar/{id}")
    public void finalizar(@PathVariable("id") int id) {
        Task task = taskService.findById(id);
        task.setFinalizada(!task.isFinalizada());
        taskService.insertOrUpdate(task);
    }

    @GetMapping("/finalizados")
    public List<Task> finalizadas(@RequestParam("finalizada") boolean finalizada) {
        return taskService.findByFinalizada(finalizada);
    }
}
