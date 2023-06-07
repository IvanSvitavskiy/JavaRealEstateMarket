/**
 * Сервис для работы с пользовательскими данными.
 */
package com.example.RealEstateMarket.Services;

import com.example.RealEstateMarket.Models.Role;
import com.example.RealEstateMarket.Models.User;
import com.example.RealEstateMarket.Repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * Метод для создания нового пользователя.
     *
     * @param user - объект пользователя, который необходимо создать.
     * @return - true, если пользователь успешно создан, false, если пользователь с такой электронной почтой уже существует.
     */
    public boolean createUser(User user){
        if(userRepository.findByEmail(user.getEmail()) != null){
            return false;
        }
        user.setActive(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.getRoles().add(Role.ROLE_USER);
        userRepository.save(user);
        return true;
    }

    /**
     * Метод для получения пользователя по идентификатору.
     *
     * @param id - идентификатор пользователя.
     * @return - найденный пользовательский объект или null, если пользователь не найден.
     */
    public User getUser(Long id){
        return userRepository.findById(id).orElse(null);
    }

    /**
     * Метод для получения списка всех пользователей.
     *
     * @return - список пользователей.
     */
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    /**
     * Метод для блокировки/разблокировки пользователя.
     *
     * @param id - идентификатор пользователя.
     */
    public void ban(Long id){
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            if(user.isActive()) user.setActive(false);
            else user.setActive(true);
            userRepository.save(user);
        }
    }


    /**
     * Метод для получения пользователя по принципалу.
     *
     * @param principal - принципал пользователя.
     * @return - найденный пользовательский объект или новый объект User, если принципал равен null.
     */
    public User getUserByPrincipal(Principal principal) {
        if (principal == null) return new User();
        return userRepository.findByEmail(principal.getName());
    }
}