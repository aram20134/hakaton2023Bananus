package me.reclaite.bananosbackend.controller;

import lombok.RequiredArgsConstructor;
import me.reclaite.bananosbackend.model.apartment.UserApartment;
import me.reclaite.bananosbackend.model.company.Company;
import me.reclaite.bananosbackend.model.house.House;
import me.reclaite.bananosbackend.model.house.HouseType;
import me.reclaite.bananosbackend.model.house.Layout;
import me.reclaite.bananosbackend.model.report.Report;
import me.reclaite.bananosbackend.model.report.ReportStatus;
import me.reclaite.bananosbackend.model.user.User;
import me.reclaite.bananosbackend.repository.ApartmentRepository;
import me.reclaite.bananosbackend.repository.ReportRepository;
import me.reclaite.bananosbackend.repository.UserRepository;
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
    private final UserRepository userRepository;
    private final ApartmentRepository apartmentRepository;
    private final ReportRepository reportRepository;

    @GetMapping("/create_report")
    public String createReport(@RequestParam("user") long userId,
                               @RequestParam("description") String description) {
        User user = userRepository.getReferenceById(userId);

        Report report = new Report();
        report.setOwnerName(user.getTelegramUsername());
        report.setHouseName(user.getOwnedHouse().getHouse().getHouseName());
        report.setDescription(description);
        report.setReportStatus(ReportStatus.IN_PROCESS);

        House house = user.getOwnedHouse().getHouse();
        house.getReports().add(report);

        reportRepository.saveAndFlush(report);
        houseService.getHouseRepository().saveAndFlush(house);

        return "OK";
    }

    @GetMapping("/create_user")
    public String createUser(@RequestParam("telegramId") long id,
                             @RequestParam("houseId") long houseId,
                             @RequestParam("telegramName") String username) {
        User user = new User();
        user.setTelegramUsername(username);
        user.setTelegramId(id);

        UserApartment userApartment = new UserApartment();
        userApartment.setHouse(houseService.getHouseRepository().getReferenceById(houseId));
        userApartment.setApartment(10);

        user.setOwnedHouse(userApartment);

        userRepository.saveAndFlush(user);
        apartmentRepository.saveAndFlush(userApartment);

        return "OK";
    }

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
