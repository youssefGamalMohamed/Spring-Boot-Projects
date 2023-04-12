package com.app.todoapp.service.implementation;

import com.app.todoapp.entity.Task;
import com.app.todoapp.enums.Status;
import com.app.todoapp.models.request.TaskRequestBody;
import com.app.todoapp.repository.TaskRepository;
import com.app.todoapp.service.framework.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService implements ITaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public boolean addTask(TaskRequestBody taskRequestBody) {
        taskRepository.save(
            new Task(taskRequestBody.getSubject() , taskRequestBody.getDescription() , taskRequestBody.getStatus())
        );
        return true;
    }

    @Override
    public boolean deleteTaskWithId(Long taskId) {
        if(!taskRepository.existsById(taskId))
            return false;

        taskRepository.deleteById(taskId);
        return true;
    }

    @Override
    public boolean updateTaskWithId(Long taskId, TaskRequestBody taskRequestBody) {

        if(!taskRepository.existsById(taskId))
            return false;

        Task task = taskRepository.findById(taskId).get();
        task.setSubject(taskRequestBody.getSubject());
        task.setDescription(taskRequestBody.getDescription());

        taskRepository.save(task);
        return true;
    }

    @Override
    public List<Task> getAllTaskWithStatus(Status taskStatus) {
        return taskRepository.findAll().stream().filter(task -> task.getStatus().equals(taskStatus)).collect(Collectors.toList());
    }


}
