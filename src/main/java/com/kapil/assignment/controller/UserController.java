package com.kapil.assignment.controller;


import com.kapil.assignment.Model.JwtRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Kapil Kaushik
 * @project post-backend-java
 * @Date 12/04/23
 * @Time 5:20 pm
 */

public interface UserController {

    String createUser(@RequestParam String name, @RequestParam String email, @RequestParam String password);
    String loginUser(@RequestBody JwtRequest jwtRequest) throws Exception;


}
