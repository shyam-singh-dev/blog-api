package com.blog.blog_api.repository;

import com.blog.blog_api.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Long>
{
    // Get all posts (paginated)
    @Override
    Page<Post> findAll(Pageable pageable);

    // Get posts by user
    List<Post> findByUserId(Long userId);

    // Get posts by category
    Page<Post> findByCategoryId(Long categoryId , Pageable pageable);

    // Search posts by title or content
    @Query("SELECT p FROM Post p WHERE " +
            "LOWER(p.title) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(p.content) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    Page<Post> searchPosts(@Param("keyword") String keyword,
                           Pageable pageable);

    // Count posts by user
    Long countByUserId(Long userId);

    //  Total likes by user (sum of likeCount of all user's posts)

    @Query("SELECT COALESCE(SUM(p.likeCount), 0) FROM Post p WHERE p.user.id = :userId")
    Long getTotalLikesByUser(@Param("userId") Long userId);

    @Query("SELECT p FROM Post p ORDER BY p.likeCount DESC")
    Page<Post> findTrendingPosts(Pageable pageable);

}
