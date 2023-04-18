package com.kapil.assignment.services;

import com.kapil.assignment.dto.NewPost;
import com.kapil.assignment.entity.PostLikeEntity;
import com.kapil.assignment.entity.UserEntity;

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
}
