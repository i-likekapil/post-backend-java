package com.kapil.assignment.entity;


import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.io.Serializable;

import java.util.Date;
import java.util.List;

/**
 * @author Kapil Kaushik
 * @project post-backend-java
 * @Date 12/04/23
 * @Time 5:42 pm
 */

@Entity
public class UserEntity implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer accountId;
    String name;
    String email;
    String password;
    Date createdAt;
    @ElementCollection
    List<Integer> following;
    @ElementCollection
    List<Integer> follower;
    @ElementCollection
    List<Integer> postsLiked;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "accountId")
    List<PostEntity> posts;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "accountId")
    List<CommentEntity> comments;


}
