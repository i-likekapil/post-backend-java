package com.kapil.assignment.entity;

import lombok.Data;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author Kapil Kaushik
 * @project post-backend-java
 * @Date 12/04/23
 * @Time 5:42 pm
 */

@Entity
@Data
public class UserEntity implements Serializable {


    @Id
    Integer accountId;
    String name;
    String email;
    String password;
    String createdAt;
    @ElementCollection
    ArrayList<Integer> following;
    @ElementCollection
    ArrayList<Integer> follower;
    @ElementCollection
    ArrayList<Integer> postsLiked;
    @ElementCollection
    ArrayList<Integer> posts;
}
