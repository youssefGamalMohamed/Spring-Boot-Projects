package com.app.todoapp.service.impl;

import com.app.todoapp.entity.User;
import com.app.todoapp.entity.embedded.Name;
import com.app.todoapp.exception.models.IdNotFoundException;
import com.app.todoapp.models.request.UserRequestBody;
import com.app.todoapp.models.response.success.AddUserResponseBody;
import com.app.todoapp.models.response.success.DeleteUserResponseBody;
import com.app.todoapp.repository.UserRepo;
import com.app.todoapp.service.framework.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepo userRepo;


    @Override
    public AddUserResponseBody addNewUser(UserRequestBody userRequestBody) {
        User user = User.builder()
                .name(
                        Name.builder()
                                .firstName(userRequestBody.getFirstName())
                                .lastName(userRequestBody.getLastName())
                                .build()
                ).build();


        userRepo.save(user);

        return AddUserResponseBody.builder().id(user.getId()).build();
    }

    @Override
    public User getUserById(Long id) throws IdNotFoundException {
        if(!userRepo.existsById(id))
            throw new IdNotFoundException("user id not found");

        return userRepo.findById(id).get();
    }

    @Override
    public DeleteUserResponseBody deleteUserById(Long id) throws IdNotFoundException {
        if(!userRepo.existsById(id))
            throw new IdNotFoundException("user id not found");
        userRepo.deleteById(id);

        return DeleteUserResponseBody.builder().deletion_status(true).build();
    }


}
