package me.reclaite.bananosbackend.controller;

import lombok.RequiredArgsConstructor;
import me.reclaite.bananosbackend.model.HouseAction;
import me.reclaite.bananosbackend.model.house.House;
import me.reclaite.bananosbackend.service.HouseService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequiredArgsConstructor
public class HouseController {

    private final HouseService houseService;

    @GetMapping("/houses")
    public Collection<House> getRegisteredHouses() {
        return houseService.getHouseRepository().findAll();
    }


}
