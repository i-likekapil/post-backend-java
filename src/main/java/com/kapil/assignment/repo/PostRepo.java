package com.kapil.assignment.repo;

import com.kapil.assignment.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

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


}
