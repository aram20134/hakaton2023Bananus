package me.reclaite.bananosbackend.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import me.reclaite.bananosbackend.model.HouseAction;
import me.reclaite.bananosbackend.model.HouseAppliance;
import me.reclaite.bananosbackend.model.apartment.UserApartment;
import me.reclaite.bananosbackend.model.house.House;
import me.reclaite.bananosbackend.model.house.Layout;
import me.reclaite.bananosbackend.model.user.User;
import me.reclaite.bananosbackend.repository.UserRepository;
import me.reclaite.bananosbackend.service.HouseService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Getter
public class HouseController {

    private final HouseService houseService;
    private final UserRepository userRepository;

    @PostMapping("/housecreate")
    public House createHouse(@RequestBody HouseAction action) {
        House house = new House();

        house.setOwner(action.getOwnerCompany());
        house.setLayouts(Collections.singletonList(action.getLayout()));
        house.setAddress(action.getHouseAddress());
        house.setHouseType(action.getHouseType());

        return house;
    }

    @PostMapping("/house")
    public House editHouse(@RequestParam("id") long houseId, @RequestBody HouseAction action) {
        House house = houseService.getHouseRepository().getReferenceById(houseId);

        house.setHouseName(action.getHouseName());
        house.setAddress(action.getHouseAddress());
        house.setLayouts(Collections.singletonList(action.getLayout()));

        return house;
    }

    @PostMapping("/house_layout")
    public String editHouseLayout(@RequestParam("id") long houseId,
                                  @RequestParam("layoutPath") String layoutPath,
                                  @RequestParam("area") float area,
                                  @RequestParam("roomsAmount") int roomsAmount) {
        House house = houseService.getHouseRepository().getReferenceById(houseId);

        Layout layout = new Layout();
        layout.setPicturePath(layoutPath);
        layout.setArea(area);
        layout.setRoomsAmount(roomsAmount);

        house.setLayouts(Collections.singletonList(layout));

        return "OK";
    }

    @GetMapping("/allservices")
    public HouseAppliance getServices(@RequestParam("id") long houseId) {
        HouseAppliance houseAppliance = new HouseAppliance();

        List<UserApartment> collect = userRepository
                .findAll()
                .stream()
                .map(User::getOwnedHouse)
                .filter(ownedHouse -> ownedHouse.getHouse().getId() == houseId)
                .toList();

        for (UserApartment apartment : collect) {
            houseAppliance.setElectricity(apartment.getMetric().getElectricity());
            houseAppliance.setHeating(apartment.getMetric().getHeating());
            houseAppliance.setGas(apartment.getMetric().getGas());
            houseAppliance.setWater(apartment.getMetric().getWater());
        }

        return houseAppliance;
    }

    @GetMapping("/house")
    public House getHouse(@RequestParam("id") long houseId) {
        return houseService.getHouseRepository().findById(houseId).orElse(null);
    }

    @GetMapping("/houses")
    public Collection<House> getRegisteredHouses() {
        return houseService.getHouseRepository().findAll();
    }

    @GetMapping("/layouts")
    public Collection<Layout> getRegisteredLayouts() {
        return houseService.getLayoutRepository().findAll();
    }


}
