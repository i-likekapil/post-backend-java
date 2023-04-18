package com.kapil.assignment.repo;

import com.kapil.assignment.entity.CommentEntity;
import com.kapil.assignment.entity.PostEntity;
import com.kapil.assignment.entity.PostLikeEntity;
import com.kapil.assignment.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Kapil Kaushik
 * @project post-backend-java
 * @Date 16/04/23
 * @Time 2:23 pm
 */

@Repository
public interface LikeRepo extends JpaRepository<PostLikeEntity, Integer> {
    @Query("select (count(p) > 0) from PostLikeEntity p where p.likedBy.accountId = ?1 and p.likedOn.postId = ?2")
    boolean existsByLikedBy_AccountIdAndLikedOn_PostId(Integer accountId, Integer postId);
    @Transactional
    @Modifying
    @Query("update PostLikeEntity p set p.liked_or_disliked = ?1 where p.likedBy = ?2 and p.likedOn = ?3")
    int updateLiked_or_dislikedByLikedByAndLikedOn(Boolean liked_or_disliked, UserEntity likedBy, PostEntity likedOn);



}
