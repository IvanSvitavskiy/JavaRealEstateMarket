package com.example.RealEstateMarket.Controllers;

import com.example.RealEstateMarket.Services.BuildingService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class MainController {
    private final BuildingService buildingService;
    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("buildings", buildingService.getAllBuildings());
        return "home";
    }
}
