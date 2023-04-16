package com.kapil.assignment.entity;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author Kapil Kaushik
 * @project post-backend-java
 * @Date 13/04/23
 * @Time 11:13 am
 */

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class CommentEntity implements Serializable {

    @Id
    Integer commentId;

    @ManyToOne
    @JoinColumn(name = "comments")
    UserEntity accountId;

    @ManyToOne
    @JoinColumn(name = "postId")
    PostEntity postId;

    String commentMsg;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CommentEntity that = (CommentEntity) o;
        return commentId != null && Objects.equals(commentId, that.commentId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
