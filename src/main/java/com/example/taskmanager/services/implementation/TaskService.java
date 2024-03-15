package com.example.taskmanager.services.implementation;

import com.example.taskmanager.services.ITaskService;
import com.example.taskmanager.entities.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import com.example.taskmanager.repositories.ITaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService implements ITaskService {

    @Autowired
    @Qualifier("taskRepository")
    private ITaskRepository taskRepository;

    public Task findById (int id) {
        return taskRepository.findById(id);
    }

    public List<Task> findAll() {
        return taskRepository.findAll();
    }
    public Task insertOrUpdate (Task task) {
        taskRepository.save(task);
        return task;
    }
    public boolean remove (int id) {
        try {
            taskRepository.deleteById(id);
            return true;
        } catch(Exception e) {
            return false;
        }
    }

    public List<Task> findByFinalizada(boolean finalizado) {
        return taskRepository.findByFinalizada(finalizado);
    }

}
