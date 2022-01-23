package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.Id;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class DbService {

    private final TaskRepository repository;

//public DbService(TaskRepository repository) {
    //  this.repository = repository;

    public List<Task> getAllTasks() {
        return repository.findAll();
    }
    public Optional <Task> getTask(Long id) {//Optional<T> findById(ID id);
        return repository.findById(id);
    }
}