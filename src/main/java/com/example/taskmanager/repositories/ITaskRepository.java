package com.example.taskmanager.repositories;

import com.example.taskmanager.entities.Task;
import com.example.taskmanager.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository("taskRepository")
public interface ITaskRepository extends JpaRepository<Task, Serializable> {
    public abstract Task findById (int id);
    public abstract Task findByTitulo (String titulo);
    public abstract List<Task> findByFinalizada(boolean finalizado);
    @Query("SELECT t FROM Task t WHERE t.user = :user")
    public abstract List<Task> findByUser (UserEntity user);

}
