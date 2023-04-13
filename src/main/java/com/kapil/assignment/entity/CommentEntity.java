package com.kapil.assignment.entity;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author Kapil Kaushik
 * @project post-backend-java
 * @Date 13/04/23
 * @Time 11:13 am
 */

@Entity
public class CommentEntity implements Serializable {

    @Id
    Integer commentId;

    Integer accountId;
    Integer postId;
    String commentMsg;
}
