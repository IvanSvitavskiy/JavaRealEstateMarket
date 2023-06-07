/**
 * Сервис для работы с пользовательскими данными.
 */
package com.example.RealEstateMarket.Services;

import com.example.RealEstateMarket.Repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    private final UserRepository userRepository;

    /**
     * Метод для получения пользовательских данных по электронной почте.
     *
     * @param email - электронная почта пользователя.
     * @return - найденный пользовательский объект или исключение, если пользователь не найден.
     * @throws UsernameNotFoundException - исключение, которое может возникнуть при отсутствии пользователя в базе данных.
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email);
    }
}