package com.app.todoapp.controller.impl;

import com.app.todoapp.controller.framework.ITaskControllerInterface;
import com.app.todoapp.enums.Status;
import com.app.todoapp.exception.models.IdNotFoundException;
import com.app.todoapp.models.request.TaskRequestBody;
import com.app.todoapp.models.response.error.BadRequestResponse;
import com.app.todoapp.models.response.error.InternalServerResponse;
import com.app.todoapp.models.response.success.*;
import com.app.todoapp.service.framework.ITaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class TaskController implements ITaskControllerInterface {

    @Autowired
    private ITaskService taskService;

    @Override
    @PostMapping("/task")
    public ResponseEntity<?> addTask(@RequestBody @Valid TaskRequestBody taskRequestBody) {
        return new ResponseEntity<>(
                taskService.addTask(taskRequestBody) , HttpStatus.OK
        );
    }

    @Override
    @DeleteMapping("/task/{id}")
    public ResponseEntity<?> deleteTaskById(@PathVariable(name = "id") Long taskId) throws IdNotFoundException {
        return new ResponseEntity<>(
                taskService.deleteTaskWithId(taskId),
                HttpStatus.OK
        );
    }


    @Override
    @GetMapping("/task")
    public ResponseEntity<?> getAllTasksWithStatus(@RequestParam(value = "status") Status taskStatus) {
        return new ResponseEntity<>(
                    taskService.getAllTaskWithStatus(taskStatus),
                    HttpStatus.OK
                );
    }

    @Override
    @PutMapping("/task/{id}")
    public ResponseEntity<?> updateTask(@PathVariable(name = "id") Long taskId , @RequestBody @Valid TaskRequestBody taskRequestBody) throws IdNotFoundException {
        return new ResponseEntity<>(
                taskService.updateTaskWithId(taskId , taskRequestBody),
                HttpStatus.OK
        );
    }


    @Override
    @PutMapping("/task/{taskId}/user/{userId}")
    public ResponseEntity<?> addTaskToUser(@PathVariable("taskId") Long taskId , @PathVariable("userId") Long userId) throws IdNotFoundException {
        return new ResponseEntity<>(
                taskService.addTaskToUser(userId,taskId),
                HttpStatus.OK
        );
    }
}
