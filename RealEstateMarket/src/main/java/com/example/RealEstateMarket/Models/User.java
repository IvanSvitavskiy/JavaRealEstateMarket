/**
 * Модель пользователя в системе.
 */
package com.example.RealEstateMarket.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.*;

@Entity
@Table(name = "users")
public class User implements UserDetails {

    /**
     * Идентификатор пользователя.
     */
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    /**
     * Электронная почта пользователя.
     */
    @Getter
    @Setter
    @Column(name = "email", unique = true)
    private String email;

    /**
     * Номер телефона пользователя.
     */
    @Getter
    @Setter
    @Column(name = "phone")
    private String phone;

    /**
     * Имя пользователя.
     */
    @Getter
    @Setter
    @Column(name = "name")
    private String name;

    /**
     * Флаг активности пользователя.
     */
    @Getter
    @Setter
    @Column(name = "active")
    private boolean active;

    /**
     * Фотография пользователя.
     */
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "image_id")
    @Getter
    @Setter
    private Image photo;

    /**
     * Пароль пользователя.
     */
    @Getter
    @Setter
    @Column(name = "password", length = 500)
    private String password;

    /**
     * Роли пользователя.
     */
    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    @Getter
    @Setter
    private Set<Role> roles = new HashSet<>();

    /**
     * Список зданий, принадлежащих пользователю.
     */
    @Getter
    @Setter
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
    private List<Building> buildings = new ArrayList<>();

    /**
     * Возвращает роли пользователя.
     *
     * @return Роли пользователя.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    /**
     * Проверяет, является ли пользователь администратором.
     *
     * @return true, если пользователь является администратором, иначе false.
     */
    public boolean isAdmin(){
        return roles.contains(Role.ROLE_ADMIN);
    }

    /**
     * Возвращает имя пользователя.
     *
     * @return Имя пользователя.
     */
    @Override
    public String getUsername() {
        return email;
    }

    /**
     * Проверяет, не истек ли срок действия учетной записи пользователя.
     *
     * @return true, если срок действия учетной записи не истек, иначе false.
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Проверяет, не заблокирована ли учетная запись пользователя.
     *
     * @return true, если учетная запись не заблокирована, иначе false.
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Проверяет, не истек ли срок действия учетных данных пользователя.
     *
     * @return true, если срок действия учетных данных не истек, иначе false.
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Проверяет, активна ли учетная запись пользователя.
     *
     * @return true, если учетная запись активна, иначе false.
     */
    @Override
    public boolean isEnabled() {
        return active;
    }
}