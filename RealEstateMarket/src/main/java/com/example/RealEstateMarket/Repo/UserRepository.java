package com.example.RealEstateMarket.Repo;

import com.example.RealEstateMarket.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
