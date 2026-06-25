package com.blog.blog_api.DTO.response;


import lombok.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentResponse {

    private Long id;
    private String content;
    private LocalDateTime createdAt;

    // author info
    private Long authorId;
    private String authorName;

    // Post info
    private Long postId;
    private String postTitle;



}
