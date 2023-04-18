package com.kapil.assignment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
@ToString
public class UserPosts {

    private Integer id;
    private String title;
    private String desc;
    private Date created_At;
    private Integer likes;
    private List<String> comments;
}
