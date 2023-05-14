package com.app.todoapp.controller.framework;

import com.app.todoapp.entity.User;
import com.app.todoapp.exception.models.IdNotFoundException;
import com.app.todoapp.models.request.UserRequestBody;
import com.app.todoapp.models.response.error.BadRequestResponse;
import com.app.todoapp.models.response.error.InternalServerResponse;
import com.app.todoapp.models.response.success.AddUserResponseBody;
import com.app.todoapp.models.response.success.DeleteUserResponseBody;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface IUserControllerInterface {

    @Operation(summary = "Add New User")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "User Added Successfully",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = AddUserResponseBody.class)
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
    ResponseEntity<?> addNewUser(@Valid @RequestBody UserRequestBody userRequestBody);

    @Operation(summary = "Get User By Id")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200", description = "information about the user and his/her tasks",
                            content = {
                                    @Content(mediaType = "application/json",
                                            schema = @Schema(implementation = User.class))
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
    ResponseEntity<?> getUserById(@PathVariable(name = "id") Long id) throws IdNotFoundException;


    @Operation(summary = "Delete  User By Id")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200", description = "user deleted successfully with his/her tasks",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = DeleteUserResponseBody.class)
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
    ResponseEntity<?> deleteUserById(@PathVariable Long id) throws IdNotFoundException;
}
