package com.example.taskmanager.services.implementation;

import com.example.taskmanager.entities.UserEntity;
import com.example.taskmanager.models.TaskModel;
import com.example.taskmanager.services.ITaskService;
import com.example.taskmanager.entities.Task;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import com.example.taskmanager.repositories.ITaskRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class TaskService implements ITaskService {

    @Autowired
    @Qualifier("taskRepository")
    private ITaskRepository taskRepository;

    private ModelMapper modelMapper=new ModelMapper();

    public Task findById (int id) {
        return taskRepository.findById(id);
    }

    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    public List<Task> findByUser(UserEntity userEntity) {
        return taskRepository.findByUser(userEntity);
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
