package com.kapil.assignment.controller.impl;

import com.kapil.assignment.Model.CommentRequest;
import com.kapil.assignment.Model.PostRequest;
import com.kapil.assignment.entity.CommentEntity;
import com.kapil.assignment.entity.PostEntity;
import com.kapil.assignment.entity.UserEntity;
import com.kapil.assignment.repo.CommentRepo;
import com.kapil.assignment.repo.PostRepo;
import com.kapil.assignment.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Random;

/**
 * @author Kapil Kaushik
 * @project post-backend-java
 * @Date 12/04/23
 * @Time 5:34 pm
 */

@RestController
@RequestMapping("/api/posts")
public class PostControllerImpl {

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CommentRepo commentRepo;


    @GetMapping("/home")
    public String home() {
        return "Post Api is Running";
    }

    @PostMapping("/post")
    public String createPost(@RequestBody PostRequest postRequest) {
        try {
            System.out.println("--------------" + postRequest);
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            UserEntity user = userRepo.findUserEntitiesByEmail(auth.getName());
//            //System.out.println("user" + user);

            PostEntity post = new PostEntity();
            post.setCreatedAt(new Date());
            post.setTitle(postRequest.getTitle());
            post.setDescription(postRequest.getDesc());
            post.setCommentCount(0);
            post.setLikeCount(0);

            post.setPostedBy(user);


            //userRepo.save(user);
            postRepo.save(post);

            return post.getPostId().toString();

        } catch (Exception e) {
            System.out.println(e);
            return "error";
        }
    }

    @PostMapping("/like/{id}")
    public String postLike(@PathVariable String id) {
        int idd = Integer.parseInt(id);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserEntity user = userRepo.findUserEntitiesByEmail(auth.getName());
        //TODO: like a post from post id.


        return "liked";
    }



    @PostMapping("/comment/{id}")
    public String postComment(@PathVariable String id, @RequestBody CommentRequest commentRequest) {
        int idd = Integer.parseInt(id);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserEntity user = userRepo.findUserEntitiesByEmail(auth.getName());


        CommentEntity comment = new CommentEntity();

        comment.setCommentedAt(new Date());
        comment.setCommentedOn(new PostEntity(idd));
        comment.setCommentedBy(user);
        comment.setCommentMsg(commentRequest.getCommentMsg());

        commentRepo.save(comment);

        return "saved";
    }

}
