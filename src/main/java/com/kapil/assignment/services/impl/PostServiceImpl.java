package com.kapil.assignment.services.impl;

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
import com.kapil.assignment.services.PostService;
import com.kapil.assignment.services.PostUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author Kapil Kaushik
 * @project post-backend-java
 * @Date 18/04/23
 * @Time 4:37 pm
 */

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepo postRepo;

    @Autowired
    private LikeRepo likeRepo;

    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private PostUtil postUtil;

    @Override
    public NewPost createPost(String title, String desc, UserEntity user) {
        PostEntity post = new PostEntity(title, desc, new Date(), 0, 0);
        postRepo.save(post);
        return new NewPost(post.getPostId(),
                title,
                desc,
                post.getCreatedAt()
        );
    }


    @Override
    public boolean isPostExists(int id) {
        return postRepo.existsByPostId(id);
    }

    @Override
    public boolean isPostAlreadyLikedByUser(int postId, int accountId) {
        return likeRepo.existsByLikedBy_AccountIdAndLikedOn_PostId(accountId, postId);
    }

    @Override
    public PostLikeEntity likePostByUser(int postId, int accountId) {
        if (isPostAlreadyLikedByUser(postId,accountId)) return null;

        PostLikeEntity likeEntity = new PostLikeEntity(new PostEntity(postId), new UserEntity(accountId), new Date(), true);
        likeRepo.save(likeEntity);

        return likeEntity;
    }


    @Override
    public int unLikePostByUser(int postId, int accountId) {
        if (!isPostAlreadyLikedByUser(postId,accountId)) return -1;
        // post is liked by user now unlike
        return likeRepo.deleteByLikedByAndLikedOn(new UserEntity(accountId), new PostEntity(postId));
    }

    @Override
    public int commentPostById(int postId, int accountId, String commentMsg) {
        CommentEntity comment = new CommentEntity(
                new Date(), new PostEntity(postId), new UserEntity(accountId), commentMsg
        );
        commentRepo.save(comment);
        return comment.getCommentId();
    }

    @Override
    public PostById getAllCommentsAndLikesByPostId(int postId) {
        Optional<PostEntity> postEntity = postRepo.findById(postId);
        if (postEntity.isPresent()) {
            PostEntity post = postEntity.get();
            new PostById(postId,post.getLikeCount(), postUtil.getAllCommentsByPostId(postId));

        }
        return null;
    }

    @Override
    public List<UserPosts> getAllPostsByAccountId(int accountId) {
        List<PostEntity> allPosts = postRepo.findByPostedBy_AccountId(accountId);
        System.out.println("all posts " + allPosts);
        List<UserPosts> posts = new ArrayList<>();
        for (PostEntity post : allPosts) {
                posts.add(new UserPosts(post.getPostId(),
                        post.getTitle(), post.getDescription(), post.getCreatedAt(),
                        post.getLikeCount(), postUtil.getAllCommentsByPostId(post.getPostId())));

        }
        return posts;
    }

    @Override
    public boolean deleteUserPostById(int accountId, int postId) {
        boolean isPostExists = isPostExists(postId);
        System.out.println("isPostExists " + isPostExists);
        if (!isPostExists) return false;
        boolean isUserCreatePost = postRepo.existsByPostedBy_AccountIdAndPostId(accountId, postId);
        System.out.println("isUserCreatePost " + isUserCreatePost);
        if (!isUserCreatePost) return false;
        int result = postRepo.deleteByPostIdAndPostedBy(postId, accountId);
        System.out.println("ho gyi delete " + result);
        return true;
    }
}
