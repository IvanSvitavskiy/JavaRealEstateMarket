package com.example.RealEstateMarket.Controllers;


import com.example.RealEstateMarket.Models.Building;
import com.example.RealEstateMarket.Models.Role;
import com.example.RealEstateMarket.Models.User;
import com.example.RealEstateMarket.Services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.info.ProjectInfoProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }


    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @GetMapping("/profile")
    public String profile(Principal principal,
                          Model model) {
        User user = userService.getUserByPrincipal(principal);
        model.addAttribute("user", user);
        return "profile";
    }


    @PostMapping("/registration")
    public String createUser(User user, Model model) {
        if(!userService.createUser(user)){
            model.addAttribute("errorMessage", "Этот email уже занят!");
            return "registration";
        }
        userService.createUser(user);
        return "redirect:/login";
    }

    @GetMapping("/user/{id}")
    public String userInfo(@PathVariable Long id, Model model) {
        User user = userService.getUser(id);
        model.addAttribute("user", user);
        model.addAttribute("buildings", user.getBuildings());
        return "user-info";
    }

    @GetMapping("/admin/user/edit/{id}")
    public String userEdit(@PathVariable Long id, Model model) {
        User user = userService.getUser(id);
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "user-edit";
    }

}
