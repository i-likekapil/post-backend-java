package com.kapil.assignment.services;

import java.util.List;

/**
 * @author Kapil Kaushik
 * @project post-backend-java
 * @Date 18/04/23
 * @Time 4:37 pm
 */

public interface PostUtil {
    List<String> getAllCommentsByPostId(int id);
}
