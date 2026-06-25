package com.blog.blog_api.service;


import com.blog.blog_api.DTO.response.UserProfileResponse;
import com.blog.blog_api.entity.User;
import com.blog.blog_api.exception.ResourceNotFoundException;
import com.blog.blog_api.repository.CommentRepository;
import com.blog.blog_api.repository.PostRepository;
import com.blog.blog_api.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    public UserService(UserRepository userRepository,
                       PostRepository postRepository,
                       CommentRepository commentRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    // GET USER PROFILE with stats
    public UserProfileResponse getUserProfile(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "User not found with id: " + userId));

        // Calculate statistics
        Long totalPosts = postRepository.countByUserId(userId);
        Long totalComments = commentRepository.countByUserId(userId);
        Long totalLikes = postRepository.getTotalLikesByUser(userId);

        return UserProfileResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .memberSince(user.getCreatedAt())
                .totalPosts(totalPosts.intValue())
                .totalComments(totalComments.intValue())
                .totalLikes(totalLikes.intValue())
                .build();
    }
}
