package com.kapil.assignment.entity;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Kapil Kaushik
 * @project post-backend-java
 * @Date 13/04/23
 * @Time 10:49 am
 */

@Entity
public class PostEntity implements Serializable {

    @Id
    Integer postId;
    String title;
    String description;

    String createdAt;

    Integer accountId; // who is the owner of this post

    @ElementCollection
    List<Integer> likes = new ArrayList<>(); // store userid who likes post

    @ElementCollection
    List<Integer> comments ;
}
