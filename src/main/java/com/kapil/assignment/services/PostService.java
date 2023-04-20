package com.kapil.assignment.services;

import com.kapil.assignment.dto.NewPost;
import com.kapil.assignment.dto.PostById;
import com.kapil.assignment.dto.UserPosts;
import com.kapil.assignment.entity.PostLikeEntity;
import com.kapil.assignment.entity.UserEntity;

import java.util.List;

/**
 * @author Kapil Kaushik
 * @project post-backend-java
 * @Date 18/04/23
 * @Time 4:37 pm
 */

public interface PostService {

    NewPost createPost(String title, String desc, UserEntity user);

    boolean isPostExists(int id);

    boolean isPostAlreadyLikedByUser(int postId, int accountId);

    PostLikeEntity likePostByUser(int postId, int accountId);
    int unLikePostByUser(int postId, int accountId);

    int commentPostById(int postId, int accountId, String commentMsg);

    PostById getAllCommentsAndLikesByPostId(int postId);

    List<UserPosts> getAllPostsByAccountId(int accountId);

    boolean deleteUserPostById(int accountId, int postId);
}
