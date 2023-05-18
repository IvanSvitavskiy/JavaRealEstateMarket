package com.example.RealEstateMarket.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;


@Entity
@Table(name = "users")
public class User {
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Getter
    @Setter@Column(name = "email", unique = true)
    private String email;

    @Getter
    @Setter
    @Column(name = "phone")
    private String phone;

    @Getter
    @Setter
    @Column(name = "name")
    private String name;

    @Getter
    @Setter
    @Column(name = "isActive")
    private boolean isActive;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "image_id")
    @Getter
    @Setter
    @Column(name = "photo")
    private Image photo;

    @Getter
    @Setter
    @Column(name = "password", length = 500)
    private String password;

    private Set<Role> roles = new HashSet<>();
    private LocalDateTime createdDate;

    @PrePersist
    private void birth(){
        createdDate = LocalDateTime.now();
    }
}
