package com.app.todoapp.service.framework;

import com.app.todoapp.enums.Status;
import com.app.todoapp.exception.models.IdNotFoundException;
import com.app.todoapp.models.request.TaskRequestBody;
import com.app.todoapp.models.response.*;


public interface ITaskService {
    AddTaskResponseBody addTask(TaskRequestBody task);

    DeleteTaskResponseBody deleteTaskWithId(Long taskId) throws IdNotFoundException;

    UpdateTaskResponseBody updateTaskWithId(Long taskId, TaskRequestBody taskRequestBody) throws IdNotFoundException;


    GetAllTasksWithStatusResponseBody getAllTaskWithStatus(Status taskStatus);


    AssignTaskToUserResponseBody addTaskToUser(Long userId, Long taskId) throws IdNotFoundException;
}
