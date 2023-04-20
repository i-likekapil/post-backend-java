package com.kapil.assignment.repo;

import com.kapil.assignment.entity.PostEntity;
import com.kapil.assignment.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Kapil Kaushik
 * @project post-backend-java
 * @Date 16/04/23
 * @Time 2:23 pm
 */

@Repository
public interface PostRepo extends JpaRepository<PostEntity, Integer> {
    @Query("select (count(p) > 0) from PostEntity p where p.postId = ?1")
    boolean existsByPostId(Integer postId);

    @Query("select p from PostEntity p where p.postedBy.accountId = ?1")
    List<PostEntity> findByPostedBy_AccountId(Integer accountId);

    @Transactional
    @Modifying
    @Query("delete from PostEntity p where p.postId = ?1 and p.postedBy.accountId = ?2")
    int deleteByPostIdAndPostedBy(Integer postId, Integer accountId);

    @Query("select (count(p) > 0) from PostEntity p where p.postedBy.accountId = ?1 and p.postId = ?2")
    boolean existsByPostedBy_AccountIdAndPostId(Integer accountId, Integer postId);



}
