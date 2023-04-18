package com.kapil.assignment.controller.impl;

import com.kapil.assignment.Model.JwtRequest;
import com.kapil.assignment.config.CustomUserDetailsService;
import com.kapil.assignment.dto.UserPosts;
import com.kapil.assignment.entity.PostEntity;
import com.kapil.assignment.entity.UserEntity;
import com.kapil.assignment.jwt.JwtUtil;
import com.kapil.assignment.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kapil Kaushik
 * @project post-backend-java
 * @Date 12/04/23
 * @Time 5:34 pm
 */

@RestController
@RequestMapping("/api/user")
public class UserControllerImpl/* implements UserController */ {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private AuthenticationManager authenticationManager;


    @GetMapping("/")
    public String home() {
        return "User Api is Running";
    }


    @PostMapping("/signup")
    public String createUser(@RequestBody UserEntity user) {
        System.out.println("signup : " + user);
        userRepo.save(user);
        return "hello";
    }


    @GetMapping("/posts")
    public List<UserPosts> getAllPosts() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserEntity user = userRepo.findUserEntitiesByEmail(auth.getName());
        List<UserPosts> allUserPostList = new ArrayList<>();
        List<PostEntity> posts = user.getPosts();
        for (PostEntity post : posts) {
            System.out.println(post.getPostId());
            System.out.println("posts" + posts);
            allUserPostList.add(new UserPosts(
                    post.getPostId(),
                    post.getTitle(),
                    post.getDescription(),
                    post.getCreatedAt(),
                    post.getLikeCount(),
                    post.getCommentCount()));
        }
        return allUserPostList;
    }


    @PostMapping("/login")
    public String loginUser(@RequestBody JwtRequest jwtRequest) throws Exception {
        System.out.println("login : " + jwtRequest);
        return generateToken(jwtRequest);
    }

    private String generateToken(JwtRequest jwtRequest) throws Exception {
        try {
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getEmail(), jwtRequest.getPassword()));
        } catch (UsernameNotFoundException e) {
            throw new Exception("Bad credentials");
        }
        final UserDetails userDetails = this.customUserDetailsService.loadUserByUsername(jwtRequest.getEmail());
        System.out.println("userDetails " + userDetails);
        final String token = jwtUtil.generateToken(userDetails);
        System.out.println("token " + token);
        return token;
    }
/*
    @PostMapping("/follow/{id}")
    public String follow(@PathVariable String id) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("auth " + auth.getName());

        UserEntity user = userRepo.findUserEntitiesByEmail(auth.getName());

        System.out.println(user);
        final int accountId = Integer.parseInt(id);
        user.getFollowing().add(accountId);

        userRepo.save(user);
        final UserEntity following = userRepo.findUserEntitiesByAccountId(accountId);
        UserEntity followi = userRepo.findUserEntitiesByEmail(following.getEmail());
        System.out.println(followi);
        followi.getFollower().add(user.getAccountId());

        userRepo.save(followi);
        System.out.println("finish....");
        return null;
    }

    @PostMapping("/unfollow/{id}")
    public String unfollow(@PathVariable String id) {
        System.out.println("unfollow : " + id);
        return null;
    }

    @PostMapping("/user")
    public UserEntity user() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("auth " + auth.getName());
        return userRepo.findUserEntitiesByEmail(auth.getName());
    }
*/

}