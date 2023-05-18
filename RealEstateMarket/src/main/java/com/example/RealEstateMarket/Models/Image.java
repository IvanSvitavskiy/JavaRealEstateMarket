package com.example.RealEstateMarket.Models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "images")
public class Image {
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Getter
    @Setter
    @Column(name = "name")
    private String name;

    @Getter
    @Setter
    @Column(name = "fileName")
    private String fileName;

    @Getter
    @Setter
    @Column(name = "size")
    private Long size;

    @Getter
    @Setter
    @Column(name = "type")
    private String type;

    @Getter
    @Setter
    @Column(name = "prevIm")
    private boolean prevIm;

    @Getter
    @Setter
    @Lob
    private byte[] bytes;

    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private Building building;
}
