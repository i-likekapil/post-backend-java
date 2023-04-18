package com.kapil.assignment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 * @author Kapil Kaushik
 * @project post-backend-java
 * @Date 17/04/23
 * @Time 3:24 pm
 */

@AllArgsConstructor
@Getter
@Setter
public class UserPosts {

    private Integer postId;
    private String postTitle;
    private String postDesc;
    private Date postedOn;
    private Integer likes;
    private Integer comments;
}
