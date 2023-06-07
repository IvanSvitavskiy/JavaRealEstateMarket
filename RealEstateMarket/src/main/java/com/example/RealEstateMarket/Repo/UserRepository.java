/**
 * Интерфейс репозитория для работы с пользователями.
 */
package com.example.RealEstateMarket.Repo;

import com.example.RealEstateMarket.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Метод для поиска пользователя по электронной почте.
     *
     * @param email - электронная почта пользователя.
     * @return - найденный пользователь или null, если пользователь не найден.
     */
    User findByEmail(String email);
}