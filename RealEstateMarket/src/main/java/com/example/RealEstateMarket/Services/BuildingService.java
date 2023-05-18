package com.example.RealEstateMarket.Services;

import com.example.RealEstateMarket.Models.Building;
import com.example.RealEstateMarket.Models.Image;
import com.example.RealEstateMarket.Repo.BuildingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BuildingService {
    private final BuildingRepository buildingRepository;

    public List<Building> getAllBuildings(String title){
        if(title != null){
            return buildingRepository.findByTitle(title);
        }
        return buildingRepository.findAll();
    }

    public void addBuilding(Building b, MultipartFile f1, MultipartFile f2, MultipartFile f3) throws IOException {
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

    public void delBuilding(Long id){
        buildingRepository.deleteById(id);
    }

    public Building getBuilding(Long id){
        return buildingRepository.findById(id).orElse(null);
    }
}
