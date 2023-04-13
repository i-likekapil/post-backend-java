package com.kapil.assignment.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author Kapil Kaushik
 * @project post-backend-java
 * @Date 12/04/23
 * @Time 6:46 pm
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class JwtRequest {
    String email;
    String password;
}
