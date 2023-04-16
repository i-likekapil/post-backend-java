package com.kapil.assignment.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;


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

    Date createdAt;

    @ManyToMany(mappedBy = "postsLiked",cascade = CascadeType.ALL)
    @ToString.Exclude
    List<UserEntity> likes = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "posts")
    UserEntity accountId = null;

    @OneToMany(mappedBy = "postId")
    @ToString.Exclude
    List<CommentEntity> commentId = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PostEntity that = (PostEntity) o;
        return postId != null && Objects.equals(postId, that.postId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public List<UserEntity> getLikes() {
        return likes;
    }

    public void setLikes(List<UserEntity> likes) {
        this.likes = likes;
    }

    public UserEntity getAccountId() {
        return accountId;
    }

    public void setAccountId(UserEntity accountId) {
        this.accountId = accountId;
    }

    public List<CommentEntity> getCommentId() {
        return commentId;
    }

    public void setCommentId(List<CommentEntity> commentId) {
        this.commentId = commentId;
    }

    public PostEntity(Integer postId, String title, String description, Date createdAt, List<UserEntity> likes, UserEntity accountId, List<CommentEntity> commentId) {
        this.postId = postId;
        this.title = title;
        this.description = description;
        this.createdAt = createdAt;
        this.likes = likes;
        this.accountId = accountId;
        this.commentId = commentId;
    }

    public PostEntity() {
    }

    @Override
    public String toString() {
        return "PostEntity{" +
                "postId=" + postId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", createdAt=" + createdAt +
                ", likes=" + likes +
                ", accountId=" + accountId +
                ", commentId=" + commentId +
                '}';
    }
}
