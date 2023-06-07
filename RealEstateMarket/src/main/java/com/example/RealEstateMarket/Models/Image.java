/**
 * Класс представляет изображение, связанное с зданием на рынке недвижимости.
 */
package com.example.RealEstateMarket.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "images")
public class Image {
    /**
     * Идентификатор изображения.
     */
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    /**
     * Название изображения.
     */
    @Getter
    @Setter
    @Column(name = "name")
    private String name;

    /**
     * Название файла изображения.
     */
    @Getter
    @Setter
    @Column(name = "fileName")
    private String fileName;

    /**
     * Размер изображения.
     */
    @Getter
    @Setter
    @Column(name = "size")
    private Long size;

    /**
     * Тип изображения.
     */
    @Getter
    @Setter
    @Column(name = "type")
    private String type;

    /**
     * Флаг, указывающий, является ли изображение превью.
     */
    @Getter
    @Setter
    @Column(name = "prevIm")
    private boolean prevIm = false;

    /**
     * Байты изображения в виде массива байтов.
     */
    @Getter
    @Setter
    @Column(name = "bytes", columnDefinition = "LONGBLOB")
    @Lob
    private byte[] bytes;

    /**
     * Здание, к которому относится изображение.
     */
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @Getter
    @Setter
    private Building building;

    /**
     * Преобразует MultipartFile в объект Image.
     *
     * @param file MultipartFile для преобразования.
     * @return Объект Image, созданный из MultipartFile.
     * @throws IOException Если произошла ошибка ввода-вывода.
     */
    public static Image toImage(MultipartFile file) throws IOException {
        Image image = new Image();
        image.setName(file.getName());
        image.setFileName(file.getOriginalFilename());
        image.setType(file.getContentType());
        image.setSize(file.getSize());
        image.setBytes(file.getBytes());
        return image;
    }
}