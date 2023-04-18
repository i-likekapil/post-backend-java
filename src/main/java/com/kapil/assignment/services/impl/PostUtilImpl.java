package com.kapil.assignment.services.impl;

import com.kapil.assignment.entity.CommentEntity;
import com.kapil.assignment.entity.PostEntity;
import com.kapil.assignment.repo.CommentRepo;
import com.kapil.assignment.repo.PostRepo;
import com.kapil.assignment.services.PostUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kapil Kaushik
 * @project post-backend-java
 * @Date 18/04/23
 * @Time 4:37 pm
 */

@Service
public class PostUtilImpl implements PostUtil {

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private CommentRepo commentRepo;

    @Override
    public List<String> getAllCommentsByPostId(int id) {
        boolean isPostExists = postRepo.existsByPostId(id);
        System.out.println("post dekh le bhai hai ya nhi " + isPostExists);
        List<String> msg = new ArrayList<>();
        if (isPostExists) {
            PostEntity post = postRepo.findById(id).get();
            List<CommentEntity> commented = commentRepo.findByCommentedOn(post);
            if (post != null) {
                for (CommentEntity c : commented) msg.add(c.getCommentMsg());
            }
        }
        System.out.println("before return getAllCommentsByPostId " + msg);
        return msg;
    }
}
