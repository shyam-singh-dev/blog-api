package com.blog.blog_api.controller;


import com.blog.blog_api.DTO.request.CategoryRequest;
import com.blog.blog_api.DTO.response.ApiResponse;
import com.blog.blog_api.DTO.response.CategoryResponse;
import com.blog.blog_api.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // CREATE
    @PostMapping
    public ResponseEntity<ApiResponse<CategoryResponse>> createCategory(
            @Valid @RequestBody CategoryRequest request ) {
        CategoryResponse category = categoryService.createCategory(request);
        return new ResponseEntity<>(
                ApiResponse.success("Category created successfully ", category), HttpStatus.CREATED);

    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoryResponse>> getCategoryById(
            @PathVariable Long id ) {
        CategoryResponse category = categoryService.getCategoryById(id);
        return ResponseEntity.ok(
                ApiResponse.success("Category fetched ",category));
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoryResponse>> updateCategory(
            @PathVariable Long id,@Valid @RequestBody CategoryRequest request) {
        CategoryResponse category = categoryService.updateCategory(id, request);
        return ResponseEntity.ok(
                ApiResponse.success("Category update " , category));
    }

    // DELETE

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteCategory(
            @PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.ok(
                ApiResponse.success("Category deleted successfully"));

    }
}
