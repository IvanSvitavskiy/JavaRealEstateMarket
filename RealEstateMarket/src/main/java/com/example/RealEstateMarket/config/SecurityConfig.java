/**
 * Конфигурационный класс для настройки безопасности приложения.
 */
package com.example.RealEstateMarket.config;

import com.example.RealEstateMarket.Services.CustomUserDetailService;
import lombok.AllArgsConstructor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {

    /**
     * Сервис пользователей.
     */
    private final CustomUserDetailService userDetailService;

    /**
     * Метод настройки доступа.
     *
     * @param http - HttpSecurity объект для настройки фильтра безопасности.
     * @return SecurityFilterChain объект.
     * @throws Exception - исключение при настройке фильтра безопасности.
     */
    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/", "/registration", "/im/**").permitAll()
                        .requestMatchers("/building/**", "/images/**").permitAll()
                        .requestMatchers("/user/**").permitAll()
                        .requestMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .permitAll()
                )
                .logout((logout) -> logout.permitAll());

        return http.build();
    }

    /**
     * Метод шифрования пароля.
     *
     * @param auth - AuthenticationManagerBuilder объект для настройки.
     * @throws Exception - исключение при настройке AuthenticationManagerBuilder.
     */
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService)
                .passwordEncoder(passwordEncoder());
    }

    /**
     * Метод настройки PasswordEncoder.
     *
     * @return объект PasswordEncoder.
     */
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(8);
    }
}