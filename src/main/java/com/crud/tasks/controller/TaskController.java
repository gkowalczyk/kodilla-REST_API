package com.crud.tasks.controller;

import com.crud.tasks.domain.TaskDto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/tasks") //generowanie adresu do API
public class TaskController {

    //@RequestMapping(method = RequestMethod.GET,value = "") =  @GetMapping
    @GetMapping
    public List<TaskDto> getTasks() {
        return new ArrayList<>();
    }
    @GetMapping
    TaskDto getTask (Long taskId) {
        return new TaskDto(1L,"test title","test_content");
    }
    @DeleteMapping
    public void deleteTask(Long taskId) {

    }
    @PutMapping
    public TaskDto updateTask(TaskDto taskDto) {
        return new TaskDto(1L, "Edited test title", "Test_content");
    }
    @PostMapping
    public void createTask(TaskDto taskDto) {

    }

}
