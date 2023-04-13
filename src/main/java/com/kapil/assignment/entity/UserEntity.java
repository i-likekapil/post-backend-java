package com.kapil.assignment.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author Kapil Kaushik
 * @project post-backend-java
 * @Date 12/04/23
 * @Time 5:42 pm
 */

@Entity
@Data
public class UserEntity {


    @Id
    Integer id;
    String name;
    String email;
    String password;
    String userId;
    String createdAt;

    Integer following;
    Integer follower;
}
