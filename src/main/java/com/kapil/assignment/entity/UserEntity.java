package com.kapil.assignment.entity;


import lombok.Data;

import javax.persistence.CascadeType;
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
public class UserEntity implements Serializable {


    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer accountId;
    String name;
    String email;
    String password;
    Date createdAt = new Date();
    @ElementCollection(fetch = FetchType.EAGER)
    List<Integer> following = new ArrayList<>();
    @ElementCollection(fetch = FetchType.LAZY)
    List<Integer> follower =new ArrayList<>();
    @ManyToMany
    @JoinColumn(name = "likes")
    List<PostEntity> postsLiked =new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "accountId")
    List<PostEntity> posts = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "accountId")
    List<CommentEntity> comments = new ArrayList<>();

    @Override
    public String toString() {

        try {
            return "UserEntity{" +
                    "accountId=" + accountId +
                    ", name='" + name + '\'' +
                    ", email='" + email + '\'' +
                    ", password='" + password + '\'' +
                    ", createdAt=" + createdAt +
                    ", following=" + following +
                    ", follower=" + follower +
                    ", postsLiked=" + postsLiked +
                    ", posts=" + posts +
                    ", comments=" + comments +
                    '}';
        } catch (Exception e) {
            System.out.println("aa gyi "+e);
        }
        return "hello";
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public List<Integer> getFollowing() {
        return following;
    }

    public void setFollowing(List<Integer> following) {
        this.following = following;
    }

    public List<Integer> getFollower() {
        return follower;
    }

    public void setFollower(List<Integer> follower) {
        this.follower = follower;
    }

    public List<PostEntity> getPostsLiked() {
        return postsLiked;
    }

    public void setPostsLiked(List<PostEntity> postsLiked) {
        this.postsLiked = postsLiked;
    }

    public List<PostEntity> getPosts() {
        return posts;
    }

    public void setPosts(List<PostEntity> posts) {
        this.posts = posts;
    }

    public List<CommentEntity> getComments() {
        return comments;
    }

    public void setComments(List<CommentEntity> comments) {
        this.comments = comments;
    }
}
