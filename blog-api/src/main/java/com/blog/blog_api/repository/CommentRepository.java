package com.blog.blog_api.repository;

import com.blog.blog_api.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long>{


    List<Comment> findByPostId(Long postId);
    List<Comment> findByUserId(Long userId);

}
