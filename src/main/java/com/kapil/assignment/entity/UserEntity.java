package com.kapil.assignment.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;

import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Kapil Kaushik
 * @project post-backend-java
 * @Date 12/04/23
 * @Time 5:42 pm
 */

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Integer accountId;
    @Column(name = "name")
    String name;
    @Column(name = "email")
    String email;
    @Column(name = "password")
    String password;
    @Column(name = "createdAt")
    Date createdAt = new Date();

    @OneToMany(cascade = CascadeType.REFRESH, mappedBy = "postedBy", fetch = FetchType.LAZY)
    List<PostEntity> posts;

    public UserEntity(Integer accountId) {
        this.setAccountId(accountId);
    }

   /* @ElementCollection(fetch = FetchType.EAGER)
    List<Integer> following = new ArrayList<>();
    @ElementCollection(fetch = FetchType.LAZY)
    List<Integer> follower =new ArrayList<>();
    @ManyToMany
    @JoinColumn(name = "likes")
    List<PostEntity> postsLiked =new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "accountId")
    List<PostEntity> posts = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "accountId")
    List<CommentEntity> comments = new ArrayList<>();*/

}
