package me.reclaite.bananosbackend.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import me.reclaite.bananosbackend.model.HouseData;
import me.reclaite.bananosbackend.model.HouseAppliance;
import me.reclaite.bananosbackend.model.apartment.ApartmentMetric;
import me.reclaite.bananosbackend.model.apartment.UserApartment;
import me.reclaite.bananosbackend.model.company.Company;
import me.reclaite.bananosbackend.model.house.House;
import me.reclaite.bananosbackend.model.house.Layout;
import me.reclaite.bananosbackend.model.user.User;
import me.reclaite.bananosbackend.repository.LayoutRepository;
import me.reclaite.bananosbackend.repository.UserRepository;
import me.reclaite.bananosbackend.service.CompanyService;
import me.reclaite.bananosbackend.service.HouseService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Getter
public class HouseController {

    private final CompanyService companyService;

    private final HouseService houseService;
    private final LayoutRepository layoutRepository;
    private final UserRepository userRepository;

    @PostMapping("/house/create")
    public House createHouse(@RequestBody HouseData action) {
        House house = new House();

        Company ownerCompany = action.getOwnerCompany();
        ownerCompany.getOwnedHouses().add(house);

        house.setOwner(ownerCompany);
        house.setLayouts(Collections.singletonList(action.getLayout()));
        house.setAddress(action.getHouseAddress());
        house.setHouseType(action.getHouseType());

        houseService.getHouseRepository().saveAndFlush(house);
        companyService.getCompanyRepository().saveAndFlush(ownerCompany);

        return house;
    }

    @PostMapping("/house/edit")
    public House editHouse(@RequestBody HouseData action) {
        House house = houseService.getHouseRepository().getReferenceById(action.getId());

        house.setHouseName(action.getHouseName());
        house.setAddress(action.getHouseAddress());
        house.setLayouts(Collections.singletonList(action.getLayout()));

        return house;
    }

    @PostMapping("/house/layout")
    public String editHouseLayout(@RequestParam("id") long houseId,
                                  @RequestParam("layoutPath") String layoutPath,
                                  @RequestParam("area") float area,
                                  @RequestParam("roomsAmount") int roomsAmount) {
        House house = houseService.getHouseRepository().getReferenceById(houseId);

        Layout layout = new Layout();
        layout.setPicturePath(layoutPath);
        layout.setArea(area);
        layout.setRoomsAmount(roomsAmount);

        house.getLayouts().add(layout);

        layoutRepository.saveAndFlush(layout);
        houseService.getHouseRepository().saveAndFlush(house);

        return "OK";
    }

    @GetMapping("/house/supply/all")
    public HouseAppliance getServices(@RequestParam("id") long houseId) {
        HouseAppliance houseAppliance = new HouseAppliance();

        List<ApartmentMetric> userApartments = houseService.getHouseRepository()
                .findAll()
                .stream()
                .filter(house -> house.getId() == houseId)
                .map(House::getApartments)
                .flatMap(List::stream)
                .map(UserApartment::getMetric)
                .toList();

        for (ApartmentMetric apartmentMetric : userApartments) {
            houseAppliance.setElectricity(apartmentMetric.getElectricity());
            houseAppliance.setHeating(apartmentMetric.getHeating());
            houseAppliance.setGas(apartmentMetric.getGas());
            houseAppliance.setWater(apartmentMetric.getWater());
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
