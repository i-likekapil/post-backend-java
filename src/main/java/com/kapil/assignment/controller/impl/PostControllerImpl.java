package com.kapil.assignment.controller.impl;

import com.kapil.assignment.Model.CommentRequest;
import com.kapil.assignment.Model.PostRequest;
import com.kapil.assignment.dto.NewPost;
import com.kapil.assignment.dto.PostById;
import com.kapil.assignment.dto.UserPosts;
import com.kapil.assignment.entity.CommentEntity;
import com.kapil.assignment.entity.PostEntity;
import com.kapil.assignment.entity.PostLikeEntity;
import com.kapil.assignment.entity.UserEntity;
import com.kapil.assignment.repo.CommentRepo;
import com.kapil.assignment.repo.LikeRepo;
import com.kapil.assignment.repo.PostRepo;
import com.kapil.assignment.repo.UserRepo;
import com.kapil.assignment.services.PostUtil;
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

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

    @Autowired
    private LikeRepo likeRepo;

    @Autowired
    private PostUtil postUtil;


    @GetMapping("/home")
    public String home() {
        return "Post Api is Running";
    }

    @PostMapping("/post")
    public NewPost createPost(@RequestBody PostRequest postRequest) {
        try {
            System.out.println("--------------" + postRequest);
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            UserEntity user = userRepo.findUserEntitiesByEmail(auth.getName());

            PostEntity post = new PostEntity();
            post.setCreatedAt(new Date());
            post.setTitle(postRequest.getTitle());
            post.setDescription(postRequest.getDesc());
            post.setCommentCount(0);
            post.setLikeCount(0);
            post.setPostedBy(user);

            postRepo.save(post);

            return new NewPost(post.getPostId(),
                    post.getTitle(),
                    post.getDescription(),
                    post.getCreatedAt()
            );

        } catch (Exception e) {

            return new NewPost();
        }
    }

    @PostMapping("/like/{id}")
    public String postLike(@PathVariable String id) {
        int postId = Integer.parseInt(id);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserEntity user = userRepo.findUserEntitiesByEmail(auth.getName());
        boolean isExists = likeRepo.existsByLikedBy_AccountIdAndLikedOn_PostId(user.getAccountId(), postId);
        boolean isPostExists = postRepo.existsByPostId(postId);
        System.out.println("post dekh le bhai hai ya nhi " + isPostExists);
        if (isPostExists) {
            if (!isExists) {
                PostLikeEntity likeEntity = new PostLikeEntity();
                likeEntity.setLikedOn(new PostEntity(postId));
                likeEntity.setLikedBy(new UserEntity(user.getAccountId()));
                likeEntity.setLikedAt(new Date());
                likeEntity.setLiked_or_disliked(true);

                System.out.println(likeEntity);

                likeRepo.save(likeEntity);
                return "Post is liked by " + user.getEmail() + " ...";
            } else {
                return "Post is already liked by " + user.getEmail() + " ...";
            }
        } else {
            return "Post is not found by " + postId + " ,Please check post id.";
        }

    }

    @PostMapping("/unlike/{id}")
    public String unLikePost(@PathVariable String id) {
        int postId = Integer.parseInt(id);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserEntity user = userRepo.findUserEntitiesByEmail(auth.getName());

        boolean isExists = likeRepo.existsByLikedBy_AccountIdAndLikedOn_PostId(user.getAccountId(), postId);

        int result = -1;
        boolean isPostExists = postRepo.existsByPostId(postId);
        System.out.println("post dekh le bhai hai ya nhi " + isPostExists);
        if (isPostExists) {
            if (isExists) {
                result = likeRepo.updateLiked_or_dislikedByLikedByAndLikedOn(false, user, new PostEntity(postId));
            } else {
                // post is not liked by this user
            }
            return "unliked " + isExists + " .. " + result + "...";
        } else {
            // post not exists
        }
        return "";
    }

    @PostMapping("/comment/{id}")
    public String postComment(@PathVariable String id, @RequestBody CommentRequest commentRequest) {
        int postId = Integer.parseInt(id);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserEntity user = userRepo.findUserEntitiesByEmail(auth.getName());
        boolean isPostExists = postRepo.existsByPostId(postId);
        System.out.println("post dekh le bhai hai ya nhi " + isPostExists);
        if (isPostExists) {
            CommentEntity comment = new CommentEntity();
            comment.setCommentedAt(new Date());
            comment.setCommentedOn(new PostEntity(postId));
            comment.setCommentedBy(user);
            comment.setCommentMsg(commentRequest.getCommentMsg());
            commentRepo.save(comment);
            return comment.getCommentId()+"";
        }
        return "-1"; // change status code also with error
    }

    @GetMapping("/posts/{id}")
    public PostById getPostById(@PathVariable Integer id) {
        boolean isPostExists = postRepo.existsByPostId(id);
        System.out.println("post dekh le bhai hai ya nhi " + isPostExists);
        if (isPostExists) {
            PostEntity post = postRepo.findById(id).get();
            System.out.println("post " + post);
            List<CommentEntity> commented = commentRepo.findByCommentedOn(post);
            System.out.println(" commented " + commented);
            return new PostById(post.getLikeCount(),postUtil.getAllCommentsByPostId(id));
        }

        return new PostById();
    }

    @GetMapping("/all_posts")
    public List<UserPosts> getAllPostsByAuthUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserEntity user = userRepo.findUserEntitiesByEmail(auth.getName());
        int userid = user.getAccountId();
        List<PostEntity> allPosts = postRepo.findAll();
        System.out.println("all posts " + allPosts);
        List<UserPosts> posts = new ArrayList<>();

        for (PostEntity post : allPosts) {
            if (post.getPostedBy().getAccountId() == userid) {
                posts.add(new UserPosts(post.getPostId(),
                        post.getTitle(), post.getDescription(), post.getCreatedAt(),
                        post.getLikeCount(), postUtil.getAllCommentsByPostId(post.getPostId())));
            }
        }
        System.out.println("posts " + posts);
        return posts;
    }
}
