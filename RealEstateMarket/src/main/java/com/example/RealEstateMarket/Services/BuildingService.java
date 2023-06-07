/**
 * Сервис для работы с объектами недвижимости.
 */
package com.example.RealEstateMarket.Services;

import com.example.RealEstateMarket.Models.Building;
import com.example.RealEstateMarket.Models.Image;
import com.example.RealEstateMarket.Models.User;
import com.example.RealEstateMarket.Repo.BuildingRepository;
import com.example.RealEstateMarket.Repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BuildingService {

    private final BuildingRepository buildingRepository;
    private final UserRepository userRepository;

    /**
     * Метод для получения списка всех объектов недвижимости.
     *
     * @param title - название объекта недвижимости.
     * @return - список всех объектов недвижимости или список объектов недвижимости с указанным названием.
     */
    public List<Building> getAllBuildings(String title){
        if(title != null && !title.isEmpty()){
            return buildingRepository.findByTitle(title);
        }
        return buildingRepository.findAll();
    }

    /**
     * Метод для сохранения объекта недвижимости в базе данных.
     *
     * @param principal - текущий пользователь.
     * @param b - объект недвижимости, который нужно сохранить.
     * @param f1 - первое изображение объекта недвижимости.
     * @param f2 - второе изображение объекта недвижимости.
     * @param f3 - третье изображение объекта недвижимости.
     * @throws IOException - исключение, которое может возникнуть при работе с файлами.
     */
    public void saveBuilding(Principal principal, Building b, MultipartFile f1, MultipartFile f2, MultipartFile f3) throws IOException {
        b.setUser(getUserByPrincipal(principal));
        Image im1, im2, im3;
        if (f1.getSize() != 0){
            im1 = Image.toImage(f1);
            im1.setPrevIm(true);
            b.connectImageToBuilding(im1);
        }
        if (f2.getSize() != 0){
            im2 = Image.toImage(f2);
            if(f1.getSize() == 0){
                im2.setPrevIm(true);
            }
            b.connectImageToBuilding(im2);
        }
        if (f3.getSize() != 0){
            im3 = Image.toImage(f3);
            if(f1.getSize() == 0 && f2.getSize() == 0){
                im3.setPrevIm(true);
            }
            b.connectImageToBuilding(im3);
        }

        //save -> get -> set id -> save
        Building building = buildingRepository.save(b);
        building.setPrevImageId(building.getImages().get(0).getId());
        buildingRepository.save(b);
    }

    /**
     * Метод для получения пользователя по его электронной почте.
     *
     * @param principal - текущий пользователь.
     * @return - найденный пользователь или пустой пользователь, если пользователь не найден.
     */
    public User getUserByPrincipal(Principal principal) {
        if(principal == null){
            return new User();
        }
        return userRepository.findByEmail(principal.getName());
    }

    /**
     * Метод для удаления объекта недвижимости из базы данных.
     *
     * @param id - идентификатор объекта недвижимости, который нужно удалить.
     */
    public void delBuilding(Long id){
        buildingRepository.deleteById(id);
    }

    /**
     * Метод для получения объекта недвижимости по его идентификатору.
     *
     * @param id - идентификатор объекта недвижимости, который нужно получить.
     * @return - найденный объект недвижимости или null, если объект не найден.
     */
    public Building getBuilding(Long id){
        return buildingRepository.findById(id).orElse(null);
    }
}