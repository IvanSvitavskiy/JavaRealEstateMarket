package com.example.RealEstateMarket.Repo;

import com.example.RealEstateMarket.Models.Building;
import com.example.RealEstateMarket.Models.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
