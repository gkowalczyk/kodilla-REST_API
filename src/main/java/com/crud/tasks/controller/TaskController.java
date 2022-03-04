package com.crud.tasks.controller;
//Controller jest częścią aplikacji webowej,
// która odpowiada za otrzymywanie żądań i
// zwracanie odpowiedzi. Wewnątrz controllera
// wywoływane są metody klas implementujących
// logikę biznesową. W samym controllerze nie
// implementujemy więc logiki biznesowej, a
// jedynie delegujemy
// to zadanie do innych klas.


import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@CrossOrigin("*")
//@RequiredArgsConstructor
@RestController//wystawienie klasy na zewnątrz
@RequestMapping("/v1/tasks") //generowanie adresu do API
public class TaskController {


    private final DbService service;

    private final TaskMapper taskMapper;

    @Autowired
    public TaskController(DbService service, TaskMapper taskMapper) {
        this.service = service;
        this.taskMapper = taskMapper;
    }


    //@RequestMapping(method = RequestMethod.GET,value = "getTask") //=  @GetMapping
    @GetMapping
    public ResponseEntity<List<TaskDto>> getTasks() {
        List<Task> tasks = service.getAllTasks();
        return ResponseEntity.ok(taskMapper.mapToTaskDtoList(tasks));
    }
    @GetMapping(value = "{taskId}")
    public ResponseEntity<TaskDto> getTask(@PathVariable Long taskId) throws TaskNotFoundException {
        // try {
        // return new ResponseEntity<>(taskMapper.mapToTaskDto(service.getTask(taskId)), HttpStatus.OK);
        //} catch (TaskNotFoundException e) {
        // return new ResponseEntity<>(new TaskDto(0L, "No task with id =" + taskId, ""), HttpStatus.BAD_REQUEST);
        //}
        return ResponseEntity.ok(taskMapper.mapToTaskDto(service.getTask(taskId)));
    }

    @DeleteMapping("{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long taskId) {
        service.deleteTask(taskId);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<TaskDto> updateTask(@RequestBody TaskDto taskDto) {
        Task task = taskMapper.mapToTask(taskDto);
        Task saveTask = service.saveTask(task);
        return ResponseEntity.ok(taskMapper.mapToTaskDto(saveTask));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createTask(@RequestBody TaskDto taskDto) {
        Task task = taskMapper.mapToTask(taskDto);
        service.saveTask(task);
        return ResponseEntity.ok().build();
    }
}

