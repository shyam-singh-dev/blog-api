package com.blog.blog_api.service;

import com.blog.blog_api.DTO.request.CommentRequest;
import com.blog.blog_api.DTO.response.CommentResponse;
import com.blog.blog_api.entity.Comment;
import com.blog.blog_api.entity.Post;
import com.blog.blog_api.entity.User;
import com.blog.blog_api.exception.ResourceNotFoundException;
import com.blog.blog_api.repository.CommentRepository;
import com.blog.blog_api.repository.PostRepository;
import com.blog.blog_api.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private UserRepository userRepository;

    public CommentService(CommentRepository commentRepository,
                          PostRepository postRepository,
                          UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    // create comment
    public CommentResponse createComment (Long postId, Long userId , CommentRequest request) {

        Post post = postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException(
                "Post not found with id : " + postId));

        User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("" +
                "User Not Found With Id :  "));

        Comment comment = Comment.builder()
                .content(request.getContent())
                .post(post)
                .user(user)
                .build();
        Comment saved = commentRepository.save(comment);
        return toResponse(saved);

    }
    // GET COMMENTS BY POST
    public List<CommentResponse> getCommentsByPost(Long postId) {
        postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Post not found with id: " + postId));

        return commentRepository.findByPostId(postId).stream()
                .map(this::toResponse)
                .toList();
    }
    // GET COMMENT BY ID
    public CommentResponse getCommentById(Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Comment not found with id: " + id));
        return toResponse(comment);
    }


    // UPDATE COMMENT (owner only)
    public CommentResponse updateComment(Long id, Long userId,
                                         CommentRequest request) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Comment not found with id: " + id));

        // Authorization check
        if (!comment.getUser().getId().equals(userId)) {
            throw new ResourceNotFoundException(
                    "You don't have permission to update this comment");
        }

        comment.setContent(request.getContent());
        Comment updated = commentRepository.save(comment);
        return toResponse(updated);
    }


    // DELETE COMMENT (owner only)
    public void deleteComment(Long id, Long userId) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Comment not found with id: " + id));

        if (!comment.getUser().getId().equals(userId)) {
            throw new ResourceNotFoundException(
                    "You don't have permission to delete this comment");
        }

        commentRepository.delete(comment);
    }

    // GET USER'S COMMENTS
    public List<CommentResponse> getCommentsByUser(Long userId) {
        userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "User not found with id: " + userId));

        return commentRepository.findByUserId(userId).stream()
                .map(this::toResponse)
                .toList();
    }



    // HELPER: Entity → DTO
    private CommentResponse toResponse(Comment comment) {
        return CommentResponse.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .createdAt(comment.getCreatedAt())
                .authorId(comment.getUser().getId())
                .authorName(comment.getUser().getName())
                .postId(comment.getPost().getId())
                .postTitle(comment.getPost().getTitle())
                .build();
    }
}
