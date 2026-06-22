package com.blog.blog_api.DTO.response;


import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostResponse {

    private Long id;
    private String title;
    private String content;
    private String imageUrl;
    private Integer likeCount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Relationship info

    private String authorName;
    private Long authorId;
    private String categoryName;
    private Long categoryId;

}
