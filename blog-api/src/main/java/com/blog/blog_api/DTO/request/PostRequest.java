package com.blog.blog_api.DTO.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostRequest {

    @NotBlank(message = "Title is required")
    @Size(min = 5 , max = 200, message = "Title must be 5-200 characters")
    private String title;

    @NotBlank(message = "Content is required")
    @Size(min = 10, message = "Content must be at least 10 characters")
    private String content;

    private String imageUrl;

    @NotNull(message = "Category ID is required")
    private Long categoryId;

}
