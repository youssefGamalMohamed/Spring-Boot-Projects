package com.app.todoapp.controller.impl;

import com.app.todoapp.controller.framework.IUserControllerInterface;
import com.app.todoapp.entity.User;
import com.app.todoapp.exception.models.IdNotFoundException;
import com.app.todoapp.models.request.UserRequestBody;
import com.app.todoapp.models.response.success.AddUserResponseBody;
import com.app.todoapp.models.response.error.BadRequestResponse;
import com.app.todoapp.models.response.success.DeleteUserResponseBody;
import com.app.todoapp.models.response.error.InternalServerResponse;
import com.app.todoapp.service.framework.IUserService;
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

@RestController
public class UserController implements IUserControllerInterface {


    @Autowired
    private IUserService userService;


    @Override
    @PostMapping("/user")
    public ResponseEntity<?> addNewUser(@Valid @RequestBody UserRequestBody userRequestBody) {
        return new ResponseEntity<>(
                userService.addNewUser(userRequestBody),
                HttpStatus.OK
        );
    }



    @Override
    @GetMapping("/user/{id}")
    public ResponseEntity<?> getUserById(@PathVariable(name = "id") Long id) throws IdNotFoundException {
        return new ResponseEntity<>(
                userService.getUserById(id),
                HttpStatus.OK
        );
    }

    @Override
    @DeleteMapping("/user/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable Long id) throws IdNotFoundException {
        return new ResponseEntity<>(
                userService.deleteUserById(id),
                HttpStatus.OK
        );
    }


}
