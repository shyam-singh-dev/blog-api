package com.blog.blog_api.DTO.response;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserProfileResponse {

    private Long id;
    private String name;
    private String email;
    private LocalDateTime memberSince;

    // Statistics
    private Integer totalPosts;
    private Integer totalComments;
    private Integer totalLikes;


}
