package com.app.todoapp.service.impl;

import com.app.todoapp.entity.Task;
import com.app.todoapp.entity.User;
import com.app.todoapp.enums.Status;
import com.app.todoapp.exception.models.IdNotFoundException;
import com.app.todoapp.models.request.TaskRequestBody;
import com.app.todoapp.models.response.other.success.*;
import com.app.todoapp.repository.TaskRepo;
import com.app.todoapp.repository.UserRepo;
import com.app.todoapp.service.framework.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Service
public class TaskService implements ITaskService {

    @Autowired
    private TaskRepo taskRepo;

    @Autowired
    private UserRepo userRepo;

    @Override
    public AddTaskResponseBody addTask(TaskRequestBody taskRequestBody) {
        Task task = Task.builder()
                .subject(taskRequestBody.getSubject())
                .description(taskRequestBody.getDescription())
                .status(taskRequestBody.getStatus())
                .createdAt(LocalDateTime.now())
                .build();


        taskRepo.save(task);

        return AddTaskResponseBody.builder()
                .id(task.getId())
                .build();
    }

    @Override
    public DeleteTaskResponseBody deleteTaskWithId(Long taskId) throws IdNotFoundException {
        if(!taskRepo.existsById(taskId))
            throw new IdNotFoundException("task id not found to delete");

        taskRepo.deleteById(taskId);

        return DeleteTaskResponseBody.builder()
                .deletion_status(true)
                .build();
    }

    @Override
    public UpdateTaskResponseBody updateTaskWithId(Long taskId, TaskRequestBody taskRequestBody) throws IdNotFoundException {

        Task task = taskRepo.findById(taskId).orElseThrow(() -> new IdNotFoundException("task id not found to update"));
        task.setSubject(taskRequestBody.getSubject());
        task.setDescription(taskRequestBody.getDescription());


        taskRepo.save(task);
        return UpdateTaskResponseBody.builder()
                .updating_status(true)
                .build();
    }

    @Override
    public GetAllTasksWithStatusResponseBody getAllTaskWithStatus(Status taskStatus) {
        return GetAllTasksWithStatusResponseBody
                .builder()
                .taskList(
                        taskRepo.findAll().stream().filter(task -> task.getStatus().equals(taskStatus)).collect(Collectors.toList())
                )
                .build();
    }

    @Override
    public AssignTaskToUserResponseBody addTaskToUser(Long userId, Long taskId) throws IdNotFoundException {

        Task task = taskRepo.findById(taskId).orElseThrow(() -> new IdNotFoundException("task id not found"));
        User user = userRepo.findById(userId).orElseThrow(() -> new IdNotFoundException("user id not found"));

        task.setUser(user);

        taskRepo.save(task);

        return AssignTaskToUserResponseBody.builder()
                .assigning_status(true)
                .build();
    }
}
