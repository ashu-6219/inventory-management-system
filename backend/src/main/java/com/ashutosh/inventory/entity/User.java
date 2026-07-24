package com.ashutosh.inventory.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
// import java.util.ArrayList;
// import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "full_name", nullable = false, length = 150)
    private String fullName;

    @Column(nullable = false, unique = true, length = 50)
    private String username;

    @Column(unique = true, length = 150)
    private String email;

    @Column(nullable = false, length = 255)
    private String password;

    @Column(length = 20)
    private String phone;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "created_at",
            insertable = false,
            updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at",
            insertable = false,
            updatable = false)
    private LocalDateTime updatedAt;

    // @OneToMany(
    //         mappedBy = "user",
    //         cascade = CascadeType.ALL,
    //         orphanRemoval = true
    // )
    // @Builder.Default
    // private List<UserRole> userRoles = new ArrayList<>();
}