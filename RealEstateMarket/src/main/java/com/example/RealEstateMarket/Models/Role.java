/**
 * Перечисление представляет роли пользователей в системе.
 */
package com.example.RealEstateMarket.Models;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ROLE_USER, ROLE_ADMIN;

    /**
     * Возвращает название роли.
     *
     * @return Название роли.
     */
    @Override
    public String getAuthority() {
        return name();
    }
}