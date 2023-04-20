package com.kapil.assignment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @author Kapil Kaushik
 * @project post-backend-java
 * @Date 18/04/23
 * @Time 3:42 pm
 */

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PostById {

    Integer postId;
    Integer likes;
    List<String> comments;
}
