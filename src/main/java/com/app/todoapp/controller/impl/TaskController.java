package com.app.todoapp.controller.impl;

import com.app.todoapp.controller.framework.ITaskController;
import com.app.todoapp.enums.Status;
import com.app.todoapp.exception.models.IdNotFoundException;
import com.app.todoapp.models.request.TaskRequestBody;
import com.app.todoapp.service.framework.ITaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TaskController implements ITaskController {

    @Autowired
    private ITaskService taskService;

    @Override
    @PostMapping("/task")
    public ResponseEntity<?> addTask(@RequestBody @Valid TaskRequestBody taskRequestBody) {
        return new ResponseEntity<>(
                taskService.addTask(taskRequestBody) , HttpStatus.CREATED
        );
    }

    @Override
    @DeleteMapping("/task/{id}")
    public ResponseEntity<?> deleteTaskById(@PathVariable(name = "id") Long taskId) throws IdNotFoundException {
        return new ResponseEntity<>(
                taskService.deleteTaskWithId(taskId),
                HttpStatus.NOT_FOUND
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
