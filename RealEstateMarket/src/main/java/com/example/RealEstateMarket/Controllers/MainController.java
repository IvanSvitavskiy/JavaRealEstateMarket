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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;

@Controller
@AllArgsConstructor
public class MainController {
    private final BuildingService buildingService;

    @GetMapping("/")
    public String home(@RequestParam(name = "title", required = false) String title, Model model, Principal principal) {
        model.addAttribute("buildings", buildingService.getAllBuildings(title));
        model.addAttribute("user", buildingService.getUserByPrincipal(principal));
        return "home";
    }

    @GetMapping("/building/check/{id}")
    public String buildingInformation(@PathVariable Long id, Model model, Principal principal) {
        Building b = buildingService.getBuilding(id);
        model.addAttribute("building", b);
        model.addAttribute("images", b.getImages());
        model.addAttribute("user", buildingService.getUserByPrincipal(principal));
        return "building-info";
    }

    @PostMapping("/building/create")
    public String addBuilding(@RequestParam("file1") MultipartFile file1, @RequestParam("file2") MultipartFile file2,
                              @RequestParam("file3") MultipartFile file3, Building building,
                              Principal principal) throws IOException {
        buildingService.saveBuilding(principal, building, file1, file2, file3);
        return "redirect:/";
    }

    @PostMapping("/building/delete/{id}")
    public String delBuilding(@PathVariable Long id) {
        buildingService.delBuilding(id);
        return "redirect:/";
    }
}
