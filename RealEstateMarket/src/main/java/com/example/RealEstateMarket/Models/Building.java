package com.example.RealEstateMarket.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "buildings")
public class Building {
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Getter
    @Setter
    @Column(name = "title")
    private String title;

    @Getter
    @Setter
    @Column(name = "description", columnDefinition = "text")
    private String description;

    @Getter
    @Setter
    @Column(name = "price")
    private int price;

    @Getter
    @Setter
    @Column(name = "city")
    private String city;

    @Getter
    @Setter
    @Column(name = "address")
    private String address;

    @Getter
    @Setter
    @Column(name = "owner")
    private String owner;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "building")
    @Getter
    @Setter
    private List<Image> images = new ArrayList<>();

    @Getter
    @Setter
    private Long prevImageId;

    private LocalDateTime birthDate;

    @PrePersist
    private void birth() {
        birthDate = LocalDateTime.now();
    }

    public void connectImageToBuilding(Image image) {
        image.setBuilding(this);
        images.add(image);
    }
}
