package com.blog.blog_api.repository;

import com.blog.blog_api.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Long>
{
    // Get all posts (paginated)
    @Override
    Page<Post> findAll(Pageable pageable);

    // Get posts by user
    List<Post> findByUser(Long userId);

    // Get posts by category
    Page<Post> findByCategoryId(Long categoryId , Pageable pageable);

    // Search posts by title or content
    @Query("SELECT p FROM Post p WHERE " +
            "LOWER(p.title) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(p.content) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    Page<Post> searchPosts(@Param("keyword") String keyword,
                           Pageable pageable);
}
