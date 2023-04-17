package com.kapil.assignment.repo;

import com.kapil.assignment.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Kapil Kaushik
 * @project post-backend-java
 * @Date 14/04/23
 * @Time 4:46 pm
 */

@Repository
public interface UserRepo extends JpaRepository<UserEntity, Integer> {



    UserEntity findUserEntitiesByAccountId(Integer id);



    UserEntity findUserEntitiesByEmail(String email);
}
