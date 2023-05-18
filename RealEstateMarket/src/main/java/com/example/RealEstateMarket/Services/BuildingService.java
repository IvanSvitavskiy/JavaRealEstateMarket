package com.example.RealEstateMarket.Services;

import com.example.RealEstateMarket.Models.Building;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BuildingService {
    private List<Building> buildings = new ArrayList<>();

    public List<Building> getAllBuildings(){
        return buildings;
    }

    public void addBuilding(Building b){
        b.setId(++cur_id);
        buildings.add(b);
    }

    public void delBuilding(Long id){
        for(Building b: buildings){
            if(b.getId().equals(id)){
                buildings.remove(b);
                break;
            }
        }
    }

    public Building getBuilding(Long id){
        for(Building b: buildings){
            if(b.getId().equals(id)){
                return b;
            }
        }
        return null;
    }
}
