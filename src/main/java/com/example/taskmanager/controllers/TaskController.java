package com.example.taskmanager.controllers;

import com.example.taskmanager.entities.Task;
import com.example.taskmanager.services.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class TaskController {

    @Autowired
    @Qualifier("taskService")
    private ITaskService taskService;

    @GetMapping("/tasks")
    public List<Task> getAll () {
        return taskService.findAll();
    }

    @GetMapping("/task/{id}")
    public Task findById (@PathVariable("id") int id) {
        return taskService.findById(id);
    }

    @PostMapping("/create")
    public Task createTask (@RequestBody Task tarea) {
        return taskService.insertOrUpdate(tarea);
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
