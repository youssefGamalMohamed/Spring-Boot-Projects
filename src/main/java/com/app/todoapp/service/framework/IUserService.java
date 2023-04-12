package com.app.todoapp.service.framework;

import com.app.todoapp.entity.User;
import com.app.todoapp.exception.models.IdNotFoundException;
import com.app.todoapp.models.request.UserRequestBody;
import com.app.todoapp.models.response.AddUserResponseBody;
import com.app.todoapp.models.response.DeleteUserResponseBody;

public interface IUserService {
    AddUserResponseBody addNewUser(UserRequestBody userRequestBody);

    User getUserById(Long id) throws IdNotFoundException;

    DeleteUserResponseBody deleteUserById(Long id) throws IdNotFoundException;
}
