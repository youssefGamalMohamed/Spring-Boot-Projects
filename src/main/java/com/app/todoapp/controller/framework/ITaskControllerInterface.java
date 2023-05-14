package com.app.todoapp.controller.framework;

import com.app.todoapp.enums.Status;
import com.app.todoapp.exception.models.IdNotFoundException;
import com.app.todoapp.models.request.TaskRequestBody;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface ITaskControllerInterface {
    
    ResponseEntity<?> addTask(@RequestBody @Valid TaskRequestBody taskRequestBody);

    @DeleteMapping("/task/{id}")
    ResponseEntity<?> deleteTaskById(@PathVariable(name = "id") Long taskId) throws IdNotFoundException;

    @GetMapping("/task")
    ResponseEntity<?> getAllTasksWithStatus(@RequestParam(value = "status") Status taskStatus);

    @PutMapping("/task/{id}")
    ResponseEntity<?> updateTask(@PathVariable(name = "id") Long taskId , @RequestBody @Valid TaskRequestBody taskRequestBody) throws IdNotFoundException;

    @PutMapping("/task/{taskId}/user/{userId}")
    ResponseEntity<?> addTaskToUser(@PathVariable("taskId") Long taskId , @PathVariable("userId") Long userId) throws IdNotFoundException;
}
