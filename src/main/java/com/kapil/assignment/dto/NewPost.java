package com.kapil.assignment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @author Kapil Kaushik
 * @project post-backend-java
 * @Date 18/04/23
 * @Time 4:19 pm
 */

@AllArgsConstructor
@ToString
@NoArgsConstructor
@Getter
@Setter
public class NewPost {
    private Integer id;
    private String title;
    private String desc;
    private Date createdAt;
}