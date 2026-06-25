package com.blog.blog_api.controller;


import com.blog.blog_api.DTO.response.ApiResponse;
import com.blog.blog_api.DTO.response.UserProfileResponse;
import com.blog.blog_api.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@Tag(name = "2. Users", description = "User profile and statistics")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // GET USER PROFILE with stats
    @Operation(
            summary = "Get user profile",
            description = "Returns user details with post count, comment count, and total likes"
    )
    @GetMapping("/{userId}/profile")
    public ResponseEntity<ApiResponse<UserProfileResponse>> getUserProfile(
            @PathVariable Long userId) {

        UserProfileResponse profile = userService.getUserProfile(userId);
        return ResponseEntity.ok(
                ApiResponse.success("User profile fetched", profile));
    }
}
