package com.blog.blog_api.service;


import com.blog.blog_api.DTO.request.PostRequest;
import com.blog.blog_api.DTO.response.PostResponse;
import com.blog.blog_api.entity.Category;
import com.blog.blog_api.entity.Post;
import com.blog.blog_api.entity.User;
import com.blog.blog_api.exception.ResourceNotFoundException;
import com.blog.blog_api.repository.CategoryRepository;
import com.blog.blog_api.repository.PostRepository;
import com.blog.blog_api.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public PostService(PostRepository postRepository,UserRepository userRepository ,
                       CategoryRepository categoryRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    // CREATE POST
    public PostResponse createPost(Long userId, PostRequest request) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException(
                "User not found : " + userId
        ));

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Category not found with id: " + request.getCategoryId()));

        Post post = Post.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .imageUrl(request.getImageUrl())
                .likeCount(0)
                .user(user)
                .category(category)
                .build();

        Post saved = postRepository.save(post);
        return toResponse(saved);
    }

    // GET ALL POSTS (Paginated)
    public Page<PostResponse> getAllPosts(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size,
                Sort.by(sortBy).descending());

        Page<Post> posts = postRepository.findAll(pageable);
        return posts.map(this::toResponse);
    }

    // GET POST BY ID
    public PostResponse getPostById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Post not found with id: " + id));
        return toResponse(post);
    }

    // UPDATE POST
    public PostResponse updatePost(Long id, Long userId, PostRequest request) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Post not found with id: " + id));

        // Check if user owns the post (basic authorization)
        if (!post.getUser().getId().equals(userId)) {
            throw new ResourceNotFoundException(
                    "You don't have permission to update this post");
        }

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Category not found with id: " + request.getCategoryId()));

        post.setTitle(request.getTitle());
        post.setContent(request.getContent());
        post.setImageUrl(request.getImageUrl());
        post.setCategory(category);

        Post updated = postRepository.save(post);
        return toResponse(updated);
    }

    // DELETE POST
    public void deletePost(Long id, Long userId) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Post not found with id: " + id));

        // Check ownership
        if (!post.getUser().getId().equals(userId)) {
            throw new ResourceNotFoundException(
                    "You don't have permission to delete this post");
        }

        postRepository.delete(post);
    }

    // GET POSTS BY USER
    public List<PostResponse> getPostsByUser(Long userId) {
        userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "User not found with id: " + userId));

        return postRepository.findByUserId(userId).stream()
                .map(this::toResponse)
                .toList();
    }

    // GET POSTS BY CATEGORY (Paginated)
    public Page<PostResponse> getPostsByCategory(Long categoryId, int page, int size) {
        categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Category not found with id: " + categoryId));

        Pageable pageable = PageRequest.of(page, size,
                Sort.by("createdAt").descending());

        Page<Post> posts = postRepository.findByCategoryId(categoryId, pageable);
        return posts.map(this::toResponse);
    }

    // SEARCH POSTS
    public Page<PostResponse> searchPosts(String keyword, int page, int size) {
        Pageable pageable = PageRequest.of(page, size,
                Sort.by("createdAt").descending());

        Page<Post> posts = postRepository.searchPosts(keyword, pageable);
        return posts.map(this::toResponse);
    }

    // LIKE POST
    public PostResponse likePost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Post not found with id: " + id));

        post.setLikeCount(post.getLikeCount() + 1);
        Post updated = postRepository.save(post);
        return toResponse(updated);
    }


    // HELPER: Entity → DTO
    private PostResponse toResponse(Post post) {
        return PostResponse.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .imageUrl(post.getImageUrl())
                .likeCount(post.getLikeCount())
                .createdAt(post.getCreatedAt())
                .updatedAt(post.getUpdatedAt())
                .authorName(post.getUser().getName())
                .authorId(post.getUser().getId())
                .categoryName(post.getCategory().getName())
                .categoryId(post.getCategory().getId())
                .build();
    }

}
