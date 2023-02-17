package me.reclaite.bananosbackend.controller;

import lombok.RequiredArgsConstructor;
import me.reclaite.bananosbackend.model.house.House;
import me.reclaite.bananosbackend.model.house.HouseType;
import me.reclaite.bananosbackend.service.HouseService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DebugController {

    private final HouseService houseService;

    @GetMapping("/create_house")
    public void createHouse(@RequestParam("id") int id, @RequestParam("name") String name, @RequestParam("type") HouseType type) {
        House house = new House();
        house.setId(id);
        house.setHouseName(name);
        house.setHouseType(type);

        houseService.getHouseRepository().saveAndFlush(house);
    }
}
