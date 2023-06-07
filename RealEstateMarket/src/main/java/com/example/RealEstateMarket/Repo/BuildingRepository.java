/**
 * Интерфейс репозитория для работы с зданиями.
 */
package com.example.RealEstateMarket.Repo;

import com.example.RealEstateMarket.Models.Building;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BuildingRepository extends JpaRepository<Building, Long> {

    /**
     * Поиск зданий по названию.
     *
     * @param title Название здания.
     * @return Список зданий с указанным названием.
     */
    List<Building> findByTitle(String title);
}