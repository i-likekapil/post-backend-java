package com.kapil.assignment.controller.impl;

import com.kapil.assignment.Model.CommentRequest;
import com.kapil.assignment.Model.PostRequest;
import com.kapil.assignment.dto.NewPost;
import com.kapil.assignment.dto.PostById;
import com.kapil.assignment.dto.UserPosts;
import com.kapil.assignment.entity.PostLikeEntity;
import com.kapil.assignment.entity.UserEntity;
import com.kapil.assignment.repo.UserRepo;
import com.kapil.assignment.services.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Kapil Kaushik
 * @project post-backend-java
 * @Date 12/04/23
 * @Time 5:34 pm
 */

@RestController
@RequestMapping("/api/posts")
public class PostControllerImpl {

    private final UserRepo userRepo;

    private final PostService postService;

    public PostControllerImpl(UserRepo userRepo, PostService postService) {
        this.userRepo = userRepo;
        this.postService = postService;
    }

    @GetMapping("/home")
    public String home() {
        return "Post Api is Running";
    }

    @PostMapping("/post")
    public ResponseEntity<NewPost> createPost(@RequestBody PostRequest postRequest) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserEntity user = userRepo.findUserEntitiesByEmail(auth.getName());
        if (postRequest.getTitle() != null && postRequest.getDesc() != null)
            return new ResponseEntity<>(postService.createPost(
                    postRequest.getTitle(),
                    postRequest.getDesc(),
                    user
            ), HttpStatus.OK);

        return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
    }

    @PostMapping("/like/{postId}")
    public HttpStatus postLike(@PathVariable Integer postId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserEntity user = userRepo.findUserEntitiesByEmail(auth.getName());

        if (!postService.isPostExists(postId))
            return HttpStatus.NOT_FOUND;

        PostLikeEntity postLike = postService.likePostByUser(postId, user.getAccountId());
        if (postLike == null)
            return HttpStatus.NOT_MODIFIED;

        return HttpStatus.OK;
    }

    @PostMapping("/unlike/{id}")
    public HttpStatus unLikePost(@PathVariable Integer id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserEntity user = userRepo.findUserEntitiesByEmail(auth.getName());
        int res = postService.unLikePostByUser(id, user.getAccountId());
        if (res == -1) return HttpStatus.NOT_FOUND;
        return HttpStatus.OK;
    }

    @PostMapping("/comment/{postId}")
    public ResponseEntity<Integer> postComment(@PathVariable int postId, @RequestBody CommentRequest commentRequest) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserEntity user = userRepo.findUserEntitiesByEmail(auth.getName());
        boolean isPostExists = postService.isPostExists(postId);
        System.out.println("post dekh le bhai hai ya nhi " + isPostExists);
        if (isPostExists)
            return new ResponseEntity<>(postService.commentPostById(postId, user.getAccountId(), commentRequest.getCommentMsg()), HttpStatus.ACCEPTED);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<PostById> getPostById(@PathVariable Integer id) {
        boolean isPostExists = postService.isPostExists(id);
        System.out.println("post dekh le bhai hai ya nhi " + isPostExists);
        if (isPostExists)
            return new ResponseEntity<>(postService.getAllCommentsAndLikesByPostId(id), HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/all_posts")
    public ResponseEntity<List<UserPosts>> getAllPostsByAuthUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserEntity user = userRepo.findUserEntitiesByEmail(auth.getName());
        List<UserPosts> allPostsByAccountId = postService.getAllPostsByAccountId(user.getAccountId());
        System.out.println("aa gyi sari posts " + allPostsByAccountId);
        if (allPostsByAccountId.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(allPostsByAccountId, HttpStatus.OK);
    }

    @DeleteMapping("/posts/{postId}")
    public HttpStatus deletePost(@PathVariable Integer postId){
        if(postService.isPostExists(postId)){
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            UserEntity user = userRepo.findUserEntitiesByEmail(auth.getName());
            boolean result = postService.deleteUserPostById(user.getAccountId(), postId);
            if(result) return HttpStatus.ACCEPTED;
        }
        return HttpStatus.BAD_REQUEST;
    }
}
