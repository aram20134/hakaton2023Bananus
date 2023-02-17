package me.reclaite.bananosbackend.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import me.reclaite.bananosbackend.model.HouseAction;
import me.reclaite.bananosbackend.model.house.House;
import me.reclaite.bananosbackend.model.house.Layout;
import me.reclaite.bananosbackend.service.HouseService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequiredArgsConstructor
@Getter
public class HouseController {

    private final HouseService houseService;

    @PostMapping
    public House editHouse(@RequestParam("id") int houseId, @RequestBody HouseAction action) {
        House house = houseService.getHouseRepository().getById(houseId);

        house.setHouseName(action.getHouseName());
        house.setPrice(action.getPrice());
        house.setAddress(action.getHouseAddress());
        house.setLayoutId(action.getLayoutId());

        return house;
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
