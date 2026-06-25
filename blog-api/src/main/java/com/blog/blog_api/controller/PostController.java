package com.blog.blog_api.controller;


import com.blog.blog_api.DTO.request.PostRequest;
import com.blog.blog_api.DTO.response.ApiResponse;
import com.blog.blog_api.DTO.response.PostResponse;
import com.blog.blog_api.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@Tag(name = "4. Posts", description = "Blog post management APIs")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    // CREATE POST
    @Operation(summary = "Create new post")
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
    @Operation(summary = "Get all posts (paginated)")
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
    @Operation(summary = "Get post by ID")
    public ResponseEntity<ApiResponse<PostResponse>> getPostById(
            @PathVariable Long id) {
        PostResponse post = postService.getPostById(id);
        return ResponseEntity.ok(
                ApiResponse.success("Post fetched", post));
    }

    // UPDATE POST
    @PutMapping("/{id}")
    @Operation(summary = "Update post (owner only)")
    public ResponseEntity<ApiResponse<PostResponse>> updatePost(
            @PathVariable Long id,
            @RequestParam Long userId,
            @Valid @RequestBody PostRequest request) {

        PostResponse post = postService.updatePost(id, userId, request);
        return ResponseEntity.ok(
                ApiResponse.success("Post updated", post));
    }

    // DELETE POST
    @Operation(summary = "Delete post (owner only)")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deletePost(
            @PathVariable Long id,
            @RequestParam Long userId) {

        postService.deletePost(id, userId);
        return ResponseEntity.ok(
                ApiResponse.success("Post deleted successfully"));
    }

    // GET POSTS BY USER
    @Operation(summary = "Get posts by user")
    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse<List<PostResponse>>> getPostsByUser(
            @PathVariable Long userId) {

        List<PostResponse> posts = postService.getPostsByUser(userId);
        return ResponseEntity.ok(
                ApiResponse.success("User posts fetched", posts));
    }

    // GET POSTS BY CATEGORY
    @Operation(summary = "Get posts by category (paginated)")
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
    @Operation(summary = "Search posts by keyword")
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
    @Operation(summary = "Like a post")
    @PostMapping("/{id}/like")
    public ResponseEntity<ApiResponse<PostResponse>> likePost(
            @PathVariable Long id) {

        PostResponse post = postService.likePost(id);
        return ResponseEntity.ok(
                ApiResponse.success("Post liked", post));
    }

    // GET TRENDING POSTS
    @Operation(summary = "Get trending posts (most liked)")
    @GetMapping("/trending")
    public ResponseEntity<ApiResponse<Page<PostResponse>>> getTrendingPosts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Page<PostResponse> posts = postService.getTrendingPosts(page, size);
        return ResponseEntity.ok(
                ApiResponse.success("Trending posts fetched", posts));
    }
    }
