package com.blog.blog_api.DTO.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentRequest {

    @NotBlank(message = "Comment content is required")
    @Size(min = 1 , max = 1000 , message = "Comment must be 1-1000 characters")
    private String content;

}
