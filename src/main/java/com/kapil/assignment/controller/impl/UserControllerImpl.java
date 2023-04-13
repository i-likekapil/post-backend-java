package com.kapil.assignment.controller.impl;

import com.kapil.assignment.Model.JwtRequest;
import com.kapil.assignment.config.CustomUserDetailsService;
import com.kapil.assignment.controller.UserController;
import com.kapil.assignment.jwt.JwtUtil;
import org.hibernate.query.criteria.internal.predicate.PredicateImplementor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.CustomAutowireConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

/**
 * @author Kapil Kaushik
 * @project post-backend-java
 * @Date 12/04/23
 * @Time 5:34 pm
 */

@RestController
@RequestMapping("/api/user")
public class UserControllerImpl implements UserController {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;


    @GetMapping("/")
    public String home(){
        return "User Api is Running";
    }

    @Override
    @PostMapping("/signup")
    public String createUser(String name, String email, String password) {
        return null;
    }

    @Override
    @PostMapping("/login")
    public String loginUser(@RequestBody JwtRequest jwtRequest) throws Exception {
        System.out.println(jwtRequest);

        return generateToken(jwtRequest);
    }

    private String generateToken(JwtRequest jwtRequest) throws Exception {
        try{
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getEmail(),jwtRequest.getPassword()));
        }catch (UsernameNotFoundException e){
            throw new Exception("Bad credentials");
        }
        final UserDetails userDetails = this.customUserDetailsService.loadUserByUsername(jwtRequest.getEmail());
        final String token = jwtUtil.generateToken(userDetails);
        System.out.println(token);
        return token;
    }
}