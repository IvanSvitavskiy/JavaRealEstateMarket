package com.example.RealEstateMarket.Services;

import com.example.RealEstateMarket.Models.Building;
import com.example.RealEstateMarket.Repo.BuildingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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

    public void addBuilding(Building b){
        log.info("Saving {}", b);
        buildingRepository.save(b);
    }

    public void delBuilding(Long id){
        buildingRepository.deleteById(id);
    }

    public Building getBuilding(Long id){
        return buildingRepository.findById(id).orElse(null);
    }
}
