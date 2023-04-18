package com.kapil.assignment.services.impl;

import com.kapil.assignment.dto.NewPost;
import com.kapil.assignment.entity.PostEntity;
import com.kapil.assignment.entity.PostLikeEntity;
import com.kapil.assignment.entity.UserEntity;
import com.kapil.assignment.repo.LikeRepo;
import com.kapil.assignment.repo.PostRepo;
import com.kapil.assignment.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

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
}