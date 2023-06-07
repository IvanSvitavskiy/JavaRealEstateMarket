/**
 * Контроллер для работы с изображениями.
 */
package com.example.RealEstateMarket.Controllers;

import com.example.RealEstateMarket.Models.Image;
import com.example.RealEstateMarket.Repo.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;

@RestController
@RequiredArgsConstructor
public class ImageController {

    /**
     * Репозиторий для работы с изображениями.
     */
    private final ImageRepository imageRepository;

    /**
     * Метод для получения изображения по его идентификатору.
     *
     * @param id - идентификатор изображения.
     * @return объект ResponseEntity с данными изображения.
     */
    @GetMapping("/images/{id}")
    private ResponseEntity<?> getImageById(@PathVariable Long id) {
        Image image = imageRepository.findById(id).orElse(null);
        return ResponseEntity.ok().header("fileName", image.getFileName())
                .contentType(MediaType.valueOf(image.getType()))
                .contentLength(image.getSize())
                .body(new InputStreamResource(new ByteArrayInputStream(image.getBytes())));
    }
}