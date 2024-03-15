package com.example.taskmanager.repositories;

import com.example.taskmanager.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository("taskRepository")
public interface ITaskRepository extends JpaRepository<Task, Serializable> {
    public abstract Task findById (int id);
    public abstract Task findByTitulo (String titulo);
    public abstract List<Task> findByFinalizada(boolean finalizado);
}
