package me.reclaite.bananosbackend.controller;

import lombok.RequiredArgsConstructor;
import me.reclaite.bananosbackend.model.UserCreateAction;
import me.reclaite.bananosbackend.model.house.House;
import me.reclaite.bananosbackend.model.report.Report;
import me.reclaite.bananosbackend.model.report.ReportType;
import me.reclaite.bananosbackend.model.user.User;
import me.reclaite.bananosbackend.repository.UserRepository;
import me.reclaite.bananosbackend.service.HouseService;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    private final HouseService houseService;

    @PostMapping("/register")
    public String registerUser(@RequestBody UserCreateAction action) {
        System.out.println(action);
        User user = new User();

        user.setTelegramId(action.getTelegramId());

        House house = houseService.getHouseRepository().getHouseByName(action.getComplex());

        if (house == null) {
            return "Ошибка: такой дом не найден";
        }

        user.setOwnedHouses(Collections.singletonList(house.getId()));
        userRepository.saveAndFlush(user);

        return "OK";
    }

    @PostMapping("/report")
    public String reportProblem(@RequestParam("userId") long userId,
                                @RequestParam ReportType reportType,
                                @RequestParam String description) {
        User user = userRepository.getReferenceById(userId);

        Report report = new Report();
        report.setReporterId(user.getId());
        report.setReportType(reportType);
        report.setDescription(description);

        houseService.getReportRepository().saveAndFlush(report);

        return "OK";
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return userRepository.findAll();
    }

}
