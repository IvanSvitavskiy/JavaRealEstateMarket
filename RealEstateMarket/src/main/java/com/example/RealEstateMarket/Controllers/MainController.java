package com.example.RealEstateMarket.Controllers;

import com.example.RealEstateMarket.Models.Building;
import com.example.RealEstateMarket.Services.BuildingService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class MainController {
    private final BuildingService buildingService;
    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("buildings", buildingService.getAllBuildings());
        return "home";
    }

    @GetMapping("/building/{id}")
    public String buildingInformation(@PathVariable long id, Model model){
        model.addAttribute("building", buildingService.getBuilding(id));
        return "building-info";
    }
    @PostMapping
    public String addBuilding(Building building){
        buildingService.addBuilding(building);
        return "redirect:/";
    }

    @PostMapping("/building/delete/{id}")
    public String delBuilding(@PathVariable long id){
        buildingService.delBuilding(id);
        return "redirect:/";
    }
}
