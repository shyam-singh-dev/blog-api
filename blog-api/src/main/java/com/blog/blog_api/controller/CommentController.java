package com.blog.blog_api.controller;


import com.blog.blog_api.DTO.request.CommentRequest;
import com.blog.blog_api.DTO.response.ApiResponse;
import com.blog.blog_api.DTO.response.CommentResponse;
import com.blog.blog_api.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    // CREATE COMMENT on a post
    @PostMapping("/post/{postId}")
    public ResponseEntity<ApiResponse<CommentResponse>> createComment(
            @PathVariable Long postId,
            @RequestParam Long userId,
            @Valid @RequestBody CommentRequest request) {

        CommentResponse comment = commentService.createComment(
                postId, userId, request);
        return new ResponseEntity<>(
                ApiResponse.success("Comment added successfully", comment),
                HttpStatus.CREATED);
    }

    // GET ALL COMMENTS ON A POST
    @GetMapping("/post/{postId}")
    public ResponseEntity<ApiResponse<List<CommentResponse>>> getCommentsByPost(
            @PathVariable Long postId) {

        List<CommentResponse> comments = commentService.getCommentsByPost(postId);
        return ResponseEntity.ok(
                ApiResponse.success("Comments fetched", comments));
    }

    // GET COMMENT BY ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CommentResponse>> getCommentById(
            @PathVariable Long id) {

        CommentResponse comment = commentService.getCommentById(id);
        return ResponseEntity.ok(
                ApiResponse.success("Comment fetched", comment));
    }

    // UPDATE COMMENT
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CommentResponse>> updateComment(
            @PathVariable Long id,
            @RequestParam Long userId,
            @Valid @RequestBody CommentRequest request) {

        CommentResponse comment = commentService.updateComment(id, userId, request);
        return ResponseEntity.ok(
                ApiResponse.success("Comment updated", comment));
    }

    // DELETE COMMENT
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteComment(
            @PathVariable Long id,
            @RequestParam Long userId) {

        commentService.deleteComment(id, userId);
        return ResponseEntity.ok(
                ApiResponse.success("Comment deleted successfully"));
    }

    // GET USER'S COMMENTS
    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse<List<CommentResponse>>> getCommentsByUser(
            @PathVariable Long userId) {

        List<CommentResponse> comments = commentService.getCommentsByUser(userId);
        return ResponseEntity.ok(
                ApiResponse.success("User comments fetched", comments));
    }
}
