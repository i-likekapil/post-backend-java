package com.kapil.assignment.entity;

import javafx.geometry.Pos;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Kapil Kaushik
 * @project post-backend-java
 * @Date 13/04/23
 * @Time 11:13 am
 */

@Entity
public class CommentEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer commentId;

    @ManyToOne
    @JoinColumn(name = "comments")
    UserEntity accountId;

    @ManyToOne
    @JoinColumn(name = "postId")
    PostEntity postId;

    String commentMsg;
}
