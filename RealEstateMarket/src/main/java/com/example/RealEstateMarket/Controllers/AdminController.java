/**
 * Контроллер для работы с административной панелью.
 */
package com.example.RealEstateMarket.Controllers;

import com.example.RealEstateMarket.Services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class AdminController {

    /**
     * Сервис пользователей.
     */
    private final UserService userService;

    /**
     * Метод для отображения административной панели.
     *
     * @param model - объект Model для передачи данных на страницу.
     * @return имя страницы административной панели.
     */
    @GetMapping("/admin")
    public String admin(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "admin";
    }

    /**
     * Метод для блокировки пользователя.
     *
     * @param id - идентификатор пользователя.
     * @return перенаправление на страницу административной панели.
     */
    @PostMapping("/admin/user/ban/{id}")
    public String ban(@PathVariable Long id) {
        userService.ban(id);
        return "redirect:/admin";
    }
}