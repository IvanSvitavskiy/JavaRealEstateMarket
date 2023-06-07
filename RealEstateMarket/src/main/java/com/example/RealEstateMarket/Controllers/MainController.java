/**
 * Контроллер главной страницы и операций с объектами недвижимости.
 */
package com.example.RealEstateMarket.Controllers;

import com.example.RealEstateMarket.Models.Building;
import com.example.RealEstateMarket.Models.User;
import com.example.RealEstateMarket.Services.BuildingService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
public class MainController {
    private final BuildingService buildingService;

    /**
     * Обработка GET запроса на главную страницу.
     *
     * @param title     - параметр поиска объектов недвижимости по названию (не обязательный).
     * @param model     - модель данных для передачи данных в представление.
     * @param principal - данные пользователя.
     * @return - представление главной страницы.
     */
    @GetMapping("/")
    public String home(@RequestParam(name = "title", required = false) String title, Model model, Principal principal) {
        List<Building> buildings = buildingService.getAllBuildings("");
        if (title != null) {
            buildings = buildings.stream()
                    .filter(building -> building.getTitle().toLowerCase().contains(title.toLowerCase()))
                    .collect(Collectors.toList());
        }
        model.addAttribute("buildings", buildings);
        model.addAttribute("user", buildingService.getUserByPrincipal(principal));
        return "home";
    }

    /**
     * Обработка GET запроса на страницу информации об объекте недвижимости.
     *
     * @param id        - идентификатор объекта недвижимости.
     * @param model     - модель данных для передачи данных в представление.
     * @param principal - данные пользователя.
     * @return - представление страницы информации об объекте недвижимости.
     */
    @GetMapping("/building/check/{id}")
    public String buildingInformation(@PathVariable Long id, Model model, Principal principal) {
        Building b = buildingService.getBuilding(id);
        model.addAttribute("building", b);
        model.addAttribute("images", b.getImages());
        model.addAttribute("user", buildingService.getUserByPrincipal(principal));
        model.addAttribute("authorProduct", b.getUser());
        return "building-info";
    }

    /**
     * Обработка POST запроса на добавление нового объекта недвижимости.
     *
     * @param file1     - файл с изображением 1.
     * @param file2     - файл с изображением 2.
     * @param file3     - файл с изображением 3.
     * @param building  - данные нового объекта недвижимости.
     * @param principal - данные пользователя.
     * @return - перенаправление на главную страницу.
     * @throws IOException - ошибка ввода-вывода.
     */
    @PostMapping("/building/create")
    public String addBuilding(@RequestParam("file1") MultipartFile file1, @RequestParam("file2") MultipartFile file2,
                              @RequestParam("file3") MultipartFile file3, Building building,
                              Principal principal) throws IOException {
        buildingService.saveBuilding(principal, building, file1, file2, file3);
        return "redirect:/";
    }

    /**
     * Обработка POST запроса на удаление объекта недвижимости.
     *
     * @param id - идентификатор удаляемого объекта недвижимости.
     * @return - перенаправление на главную страницу.
     */
    @PostMapping("/building/delete/{id}")
    public String delBuilding(@PathVariable Long id) {
        buildingService.delBuilding(id);
        return "redirect:/";
    }

    /**
     * Обработка GET запроса на страницу с объектами недвижимости пользователя.
     *
     * @param principal - данные пользователя.
     * @param model     - модель данных для передачи данных в представление.
     * @return - представление страницы с объектами недвижимости пользователя.
     */
    @GetMapping("/my/buildings")
    public String userBuildings(Principal principal, Model model) {
        User user = buildingService.getUserByPrincipal(principal);
        model.addAttribute("user", user);
        model.addAttribute("buildings", user.getBuildings());
        return "my-buildings";
    }
}