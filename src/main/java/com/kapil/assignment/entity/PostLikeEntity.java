package com.kapil.assignment.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author Kapil Kaushik
 * @project post-backend-java
 * @Date 13/04/23
 * @Time 11:13 am
 */

@Entity
@Table(name = "post_like_rel")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostLikeEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer postLikeId;


    @JoinColumn(name = "liked_by",referencedColumnName = "id")
    @OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    UserEntity likedBy;


    @JoinColumn(name = "liked_on",referencedColumnName = "postId")
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    PostEntity likedOn;

    Date likedAt;

    Boolean liked_or_disliked;

    public PostLikeEntity(PostEntity postEntity, UserEntity userEntity, Date likedAt, boolean isLiked) {
        this.likedOn = postEntity;
        this.likedBy = userEntity;
        this.likedAt = likedAt;
        liked_or_disliked = isLiked;
    }
}