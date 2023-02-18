package me.reclaite.bananosbackend.controller;

import lombok.RequiredArgsConstructor;
import me.reclaite.bananosbackend.model.UserCreateAction;
import me.reclaite.bananosbackend.model.apartment.UserApartment;
import me.reclaite.bananosbackend.model.house.House;
import me.reclaite.bananosbackend.model.report.Report;
import me.reclaite.bananosbackend.model.user.User;
import me.reclaite.bananosbackend.repository.ApartmentRepository;
import me.reclaite.bananosbackend.repository.ReportRepository;
import me.reclaite.bananosbackend.repository.UserRepository;
import me.reclaite.bananosbackend.service.HouseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    private final HouseService houseService;
    private final ApartmentRepository apartmentRepository;
    private final ReportRepository reportRepository;

    @PostMapping("/complex")
    public String getComplexExistence(@RequestBody UserCreateAction action) {
        System.out.println(action);
        User user = new User();

        user.setTelegramId(action.getTelegramId());
        user.setTelegramUsername(action.getTelegramUsername());

        House house = houseService.getHouseRepository().getHouseByName(action.getComplex().toLowerCase());

        if (house == null) {
            return "Ошибка: такой дом не найден";
        }

        houseService.getHouseRepository().saveAndFlush(house);

        UserApartment apartment = new UserApartment();
        apartment.setHouse(house);
        apartmentRepository.saveAndFlush(apartment);

        user.setOwnedHouse(apartment);
        userRepository.saveAndFlush(user);

        return "OK";
    }

    @PostMapping("/register")
    public String registerUser(@RequestBody UserCreateAction action) {
        User user = userRepository.getReferenceById(action.getTelegramId());

        House house = user.getOwnedHouse().getHouse();

        UserApartment userApartment = new UserApartment();
        userApartment.setApartment(action.getApartment());
        userApartment.setLayout(house.getLayouts().stream().findAny().orElse(null));

        user.setOwnedHouse(userApartment);

        userRepository.saveAndFlush(user);

        return "OK";
    }

    // TODO: It should be in object for the security
    @PostMapping("/report")
    public String reportProblem(@RequestParam("userId") long userId,
                                @RequestParam String description) {
        User user = userRepository.getReferenceById(userId);

        Report report = new Report();
        report.setReporterId(user.getId());
        report.setDescription(description);

        user.getOwnedHouse().getHouse().getReports().add(report);

        reportRepository.saveAndFlush(report);
        userRepository.saveAndFlush(user);
        houseService.getReportRepository().saveAndFlush(report);

        return "OK";
    }

    @GetMapping("/reports")
    public List<Report> getReports(@RequestParam("id") long id) {
        House house = houseService.getHouseRepository().getReferenceById(id);
        return house.getReports();
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return userRepository.findAll();
    }

}
