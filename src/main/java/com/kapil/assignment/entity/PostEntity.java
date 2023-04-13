package com.kapil.assignment.entity;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * @author Kapil Kaushik
 * @project post-backend-java
 * @Date 13/04/23
 * @Time 10:49 am
 */

@Entity
public class PostEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer postId;
    String title;
    String description;

    Date createdAt;

    @ElementCollection
    List<Integer> likes;

    @ManyToOne
    @JoinColumn(name = "posts")
    UserEntity accountId;

    @OneToMany(mappedBy = "postId")
    List<CommentEntity> commentId;

}
