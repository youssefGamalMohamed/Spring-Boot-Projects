package com.app.todoapp.controller.framework;

import com.app.todoapp.enums.Status;
import com.app.todoapp.exception.models.IdNotFoundException;
import com.app.todoapp.models.request.TaskRequestBody;
import com.app.todoapp.models.response.http.BadRequestResponse;
import com.app.todoapp.models.response.http.InternalServerResponse;
import com.app.todoapp.models.response.other.success.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface ITaskController {


    @Operation(summary = "Add New Task")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "Task Added Successfully",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = AddTaskResponseBody.class)
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "400", description = "Validation Error",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = BadRequestResponse.class))
                    }
            )
    }
    )
    ResponseEntity<?> addTask(@RequestBody @Valid TaskRequestBody taskRequestBody);




    @Operation(summary = "Delete Task By Id")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200", description = "task deleted successfully",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = DeleteTaskResponseBody.class)
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "400", description = "Validation Error",
                            content = {
                                    @Content(mediaType = "application/json",
                                            schema = @Schema(implementation = BadRequestResponse.class))
                            }
                    ),
                    @ApiResponse(
                            responseCode = "500", description = "Internal Server Error",
                            content = {
                                    @Content(mediaType = "application/json",
                                            schema = @Schema(implementation = InternalServerResponse.class))
                            }
                    )
            }
    )
    ResponseEntity<?> deleteTaskById(@PathVariable(name = "id") Long taskId) throws IdNotFoundException;


    @Operation(summary = "Get All Tasks By Status")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200", description = "tasks retrieved successfully",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = GetAllTasksWithStatusResponseBody.class)
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "500", description = "Internal Server Error",
                            content = {
                                    @Content(mediaType = "application/json",
                                            schema = @Schema(implementation = InternalServerResponse.class))
                            }
                    )
            }
    )
    ResponseEntity<?> getAllTasksWithStatus(@RequestParam(value = "status") Status taskStatus);



    @Operation(summary = "Update Task By Id")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200", description = "task updated successfully",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = UpdateTaskResponseBody.class)
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "400", description = "Validation Error",
                            content = {
                                    @Content(mediaType = "application/json",
                                            schema = @Schema(implementation = BadRequestResponse.class))
                            }
                    ),
                    @ApiResponse(
                            responseCode = "500", description = "Internal Server Error",
                            content = {
                                    @Content(mediaType = "application/json",
                                            schema = @Schema(implementation = InternalServerResponse.class))
                            }
                    )
            }
    )
    ResponseEntity<?> updateTask(@PathVariable(name = "id") Long taskId , @RequestBody @Valid TaskRequestBody taskRequestBody) throws IdNotFoundException;



    @Operation(summary = "Assign Task with id to User with Id")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200", description = "task assigned successfully",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = AssignTaskToUserResponseBody.class)
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "400", description = "Validation Error",
                            content = {
                                    @Content(mediaType = "application/json",
                                            schema = @Schema(implementation = BadRequestResponse.class))
                            }
                    ),
                    @ApiResponse(
                            responseCode = "500", description = "Internal Server Error",
                            content = {
                                    @Content(mediaType = "application/json",
                                            schema = @Schema(implementation = InternalServerResponse.class))
                            }
                    )
            }
    )
    ResponseEntity<?> addTaskToUser(@PathVariable("taskId") Long taskId , @PathVariable("userId") Long userId) throws IdNotFoundException;
}
