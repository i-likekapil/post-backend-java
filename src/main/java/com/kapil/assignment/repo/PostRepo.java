package com.kapil.assignment.repo;

import com.kapil.assignment.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

/**
 * @author Kapil Kaushik
 * @project post-backend-java
 * @Date 16/04/23
 * @Time 2:23 pm
 */

@Service
public interface PostRepo extends JpaRepository<PostEntity, Integer> {


}
