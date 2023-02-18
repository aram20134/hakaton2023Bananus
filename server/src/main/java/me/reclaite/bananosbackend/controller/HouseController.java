package me.reclaite.bananosbackend.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import me.reclaite.bananosbackend.model.HouseAction;
import me.reclaite.bananosbackend.model.house.House;
import me.reclaite.bananosbackend.model.house.Layout;
import me.reclaite.bananosbackend.service.HouseService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Collections;

@RestController
@RequiredArgsConstructor
@Getter
public class HouseController {

    private final HouseService houseService;

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
