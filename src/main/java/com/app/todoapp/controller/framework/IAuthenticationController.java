package com.app.todoapp.controller.framework;

import com.app.todoapp.models.request.LoginRequestBody;
import com.app.todoapp.models.request.RegisterRequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface IAuthenticationController {

    ResponseEntity<?> register(@RequestBody RegisterRequestBody registerRequestBody);

    public ResponseEntity<?> login(@RequestBody LoginRequestBody loginRequestBody);
}
