/**
 * Класс представляет здание на рынке недвижимости.
 */
package com.example.RealEstateMarket.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "buildings")
public class Building {
    /**
     * Идентификатор здания.
     */
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    /**
     * Название здания.
     */
    @Getter
    @Setter
    @Column(name = "title")
    private String title;

    /**
     * Описание здания.
     */
    @Getter
    @Setter
    @Column(name = "description", columnDefinition = "text")
    private String description;

    /**
     * Цена здания.
     */
    @Getter
    @Setter
    @Column(name = "price")
    private int price;

    /**
     * Город, в котором находится здание.
     */
    @Getter
    @Setter
    @Column(name = "city")
    private String city;

    /**
     * Адрес здания.
     */
    @Setter
    @Column(name = "address")
    private String address;

    /**
     * Список изображений, связанных с зданием.
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "building")
    @Getter
    @Setter
    private List<Image> images = new ArrayList<>();

    /**
     * Идентификатор изображения на превью, связанного с зданием.
     */
    @Getter
    @Setter
    private Long prevImageId;

    /**
     * Пользователь, владеющий зданием.
     */
    @Getter
    @Setter
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn
    private User user;

    /**
     * Связывает изображение с зданием.
     *
     * @param image Изображение для связывания с зданием.
     */
    public void connectImageToBuilding(Image image) {
        image.setBuilding(this);
        images.add(image);
    }

    /**
     * Возвращает адрес здания или пустую строку, если он равен null.
     *
     * @return Адрес здания или пустую строку, если он равен null.
     */
    public String getAddress(){
        if(address == null){
            return "";
        }
        return address;
    }
}