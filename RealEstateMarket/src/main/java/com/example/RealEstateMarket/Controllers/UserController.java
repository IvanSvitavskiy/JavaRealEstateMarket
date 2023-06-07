/**
 * Контроллер для работы с пользователями.
 */
package com.example.RealEstateMarket.Controllers;

import com.example.RealEstateMarket.Models.Role;
import com.example.RealEstateMarket.Models.User;
import com.example.RealEstateMarket.Services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    /**
     * Обработка GET запроса на страницу входа в систему.
     * @return - представление страницы входа в систему.
     */
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    /**
     * Обработка GET запроса на страницу регистрации нового пользователя.
     * @return - представление страницы регистрации нового пользователя.
     */
    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    /**
     * Обработка GET запроса на страницу профиля пользователя.
     * @param principal - данные пользователя.
     * @param model - модель данных для передачи данных в представление.
     * @return - представление страницы профиля пользователя.
     */
    @GetMapping("/profile")
    public String profile(Principal principal, Model model) {
        User user = userService.getUserByPrincipal(principal);
        model.addAttribute("user", user);
        return "profile";
    }

    /**
     * Обработка POST запроса на создание нового пользователя.
     * @param user - данные нового пользователя.
     * @param model - модель данных для передачи данных в представление.
     * @return - перенаправление на страницу входа в систему.
     */
    @PostMapping("/registration")
    public String createUser(User user, Model model) {
        if(!userService.createUser(user)){
            model.addAttribute("errorMessage", "Этот email уже занят!");
            return "registration";
        }
        userService.createUser(user);
        return "redirect:/login";
    }

    /**
     * Обработка GET запроса на страницу информации о пользователе.
     * @param id - идентификатор пользователя.
     * @param model - модель данных для передачи данных в представление.
     * @return - представление страницы информации о пользователе.
     */
    @GetMapping("/user/{id}")
    public String userInfo(@PathVariable Long id, Model model) {
        User user = userService.getUser(id);
        model.addAttribute("user", user);
        model.addAttribute("buildings", user.getBuildings());
        return "user-info";
    }

    /**
     * Обработка GET запроса на страницу редактирования информации о пользователе администратором.
     * @param id - идентификатор пользователя.
     * @param model - модель данных для передачи данных в представление.
     * @return - представление страницы редактирования информации о пользователе.
     */
    @GetMapping("/admin/user/edit/{id}")
    public String userEdit(@PathVariable Long id, Model model) {
        User user = userService.getUser(id);
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "user-edit";
    }
}