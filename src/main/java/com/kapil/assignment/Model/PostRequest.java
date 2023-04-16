package com.kapil.assignment.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author Kapil Kaushik
 * @project post-backend-java
 * @Date 16/04/23
 * @Time 2:25 pm
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PostRequest {

    String title;
    String desc;
}
