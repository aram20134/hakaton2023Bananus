package me.reclaite.bananosbackend.controller;

import lombok.RequiredArgsConstructor;
import me.reclaite.bananosbackend.model.company.Company;
import me.reclaite.bananosbackend.model.house.House;
import me.reclaite.bananosbackend.model.house.HouseType;
import me.reclaite.bananosbackend.model.house.Layout;
import me.reclaite.bananosbackend.service.CompanyService;
import me.reclaite.bananosbackend.service.HouseService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DebugController {

    private final CompanyService companyService;
    private final HouseService houseService;

    @GetMapping("/create_house")
    public String createHouse(@RequestParam("name") String name,
                              @RequestParam("companyId") long companyId,
                              @RequestParam("address") String address,
                              @RequestParam("type") HouseType type) {
        House house = new House();

        house.setOwner(companyService.getCompanyRepository().getReferenceById(companyId));
        house.setAddress(address);
        house.setHouseName(name);
        house.setHouseType(type);

        houseService.getHouseRepository().saveAndFlush(house);
        return "OK";
    }

    @GetMapping("/create_company")
    public String createCompany(@RequestParam("name") String name) {
        Company company = new Company();

        company.setName(name);

        companyService.getCompanyRepository().saveAndFlush(company);

        return "OK";
    }

    @GetMapping("/create_layout")
    public String createLayout(@RequestParam("roomsAmount") int roomsAmount,
                               @RequestParam("path") String picturePath,
                               @RequestParam("area") float area) {
        Layout layout = new Layout();

        layout.setRoomsAmount(roomsAmount);
        layout.setPicturePath(picturePath);
        layout.setArea(area);

        houseService.getLayoutRepository().saveAndFlush(layout);
        return "OK";
    }
}
