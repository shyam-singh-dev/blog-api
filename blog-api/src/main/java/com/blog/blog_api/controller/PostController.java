package com.blog.blog_api.controller;


import com.blog.blog_api.DTO.request.PostRequest;
import com.blog.blog_api.DTO.response.ApiResponse;
import com.blog.blog_api.DTO.response.PostResponse;
import com.blog.blog_api.service.PostService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    // CREATE POST
    @PostMapping
    public ResponseEntity<ApiResponse<PostResponse>> createPost(
            @RequestParam Long userId,
            @Valid @RequestBody PostRequest request) {

        PostResponse post = postService.createPost(userId, request);
        return new ResponseEntity<>(
                ApiResponse.success("Post created successfully", post),
                HttpStatus.CREATED);
    }

    // GET ALL POSTS (Paginated)
    @GetMapping
    public ResponseEntity<ApiResponse<Page<PostResponse>>> getAllPosts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "createdAt") String sortBy) {

        Page<PostResponse> posts = postService.getAllPosts(page, size, sortBy);
        return ResponseEntity.ok(
                ApiResponse.success("Posts fetched", posts));
    }

    // GET POST BY ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PostResponse>> getPostById(
            @PathVariable Long id) {
        PostResponse post = postService.getPostById(id);
        return ResponseEntity.ok(
                ApiResponse.success("Post fetched", post));
    }

    // UPDATE POST
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<PostResponse>> updatePost(
            @PathVariable Long id,
            @RequestParam Long userId,
            @Valid @RequestBody PostRequest request) {

        PostResponse post = postService.updatePost(id, userId, request);
        return ResponseEntity.ok(
                ApiResponse.success("Post updated", post));
    }

    // DELETE POST
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deletePost(
            @PathVariable Long id,
            @RequestParam Long userId) {

        postService.deletePost(id, userId);
        return ResponseEntity.ok(
                ApiResponse.success("Post deleted successfully"));
    }

    // GET POSTS BY USER
    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse<List<PostResponse>>> getPostsByUser(
            @PathVariable Long userId) {

        List<PostResponse> posts = postService.getPostsByUser(userId);
        return ResponseEntity.ok(
                ApiResponse.success("User posts fetched", posts));
    }

    // GET POSTS BY CATEGORY
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<ApiResponse<Page<PostResponse>>> getPostsByCategory(
            @PathVariable Long categoryId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Page<PostResponse> posts = postService
                .getPostsByCategory(categoryId, page, size);
        return ResponseEntity.ok(
                ApiResponse.success("Category posts fetched", posts));
    }

    // SEARCH POSTS
    @GetMapping("/search")
    public ResponseEntity<ApiResponse<Page<PostResponse>>> searchPosts(
            @RequestParam String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Page<PostResponse> posts = postService.searchPosts(keyword, page, size);
        return ResponseEntity.ok(
                ApiResponse.success("Search results", posts));
    }

    // LIKE POST
    @PostMapping("/{id}/like")
    public ResponseEntity<ApiResponse<PostResponse>> likePost(
            @PathVariable Long id) {

        PostResponse post = postService.likePost(id);
        return ResponseEntity.ok(
                ApiResponse.success("Post liked", post));
    }

    // GET TRENDING POSTS
    @GetMapping("/trending")
    public ResponseEntity<ApiResponse<Page<PostResponse>>> getTrendingPosts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Page<PostResponse> posts = postService.getTrendingPosts(page, size);
        return ResponseEntity.ok(
                ApiResponse.success("Trending posts fetched", posts));
    }
    }
