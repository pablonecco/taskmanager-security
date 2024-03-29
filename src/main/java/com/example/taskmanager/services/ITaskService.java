package com.example.taskmanager.services;

import com.example.taskmanager.entities.Task;
import com.example.taskmanager.entities.UserEntity;
import com.example.taskmanager.models.TaskModel;

import java.util.List;

public interface ITaskService {
    public abstract Task findById (int id);
    public abstract List<Task> findAll();
    public abstract List<Task> findByFinalizada(boolean finalizado);
    public abstract List<Task> findByUser(UserEntity user);
    public abstract Task insertOrUpdate (Task task);
    public abstract boolean remove (int id);
}
