package me.reclaite.bananosbackend.controller;

import lombok.RequiredArgsConstructor;
import me.reclaite.bananosbackend.model.report.ReportInfo;
import me.reclaite.bananosbackend.model.UserCreateAction;
import me.reclaite.bananosbackend.model.apartment.ApartmentMetric;
import me.reclaite.bananosbackend.model.apartment.UserApartment;
import me.reclaite.bananosbackend.model.house.House;
import me.reclaite.bananosbackend.model.report.Report;
import me.reclaite.bananosbackend.model.report.ReportStatus;
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
    private final ApartmentMetricRepository metricRepository;
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

        ApartmentMetric metric = new ApartmentMetric();
        metric.setElectricity(ApartmentMetric.getValue());
        metric.setGas(ApartmentMetric.getValue());
        metric.setHeating(ApartmentMetric.getValue());
        metric.setWater(ApartmentMetric.getValue());

        metricRepository.saveAndFlush(metric);

        apartment.setMetric(metric);

        apartmentRepository.saveAndFlush(apartment);

        user.setOwnedHouse(apartment);
        userRepository.saveAndFlush(user);

        return "OK";
    }

    @PostMapping("/register")
    public String registerUser(@RequestBody UserCreateAction action) {
        User user = userRepository.getReferenceById(action.getTelegramId());

        UserApartment userApartment = user.getOwnedHouse();
        House house = userApartment.getHouse();

        userApartment.setApartment(action.getApartment());
        userApartment.setLayout(house.getLayouts().stream().findAny().orElse(null));

        user.setOwnedHouse(userApartment);

        userRepository.saveAndFlush(user);

        return "OK";
    }

    @PostMapping("/report")
    public String reportProblem(@RequestBody ReportInfo reportInfo) {
        User user = userRepository.getByTelegramId(reportInfo.getTelegramId());

        Report report = new Report();
        report.setOwnerName(user.getTelegramUsername());
        report.setHouseName(user.getOwnedHouse().getHouse().getHouseName());
        report.setDescription(reportInfo.getDescription());
        report.setReportStatus(ReportStatus.IN_PROCESS);

        user.getOwnedHouse().getHouse().getReports().add(report);

        reportRepository.saveAndFlush(report);
        userRepository.saveAndFlush(user);
        houseService.getReportRepository().saveAndFlush(report);

        return "OK";
    }

    @GetMapping("/report_status")
    public String changeReportStatus(@RequestParam("id") long reportId,
                                     @RequestParam("status") ReportStatus status) {
        Report report = reportRepository.getReferenceById(reportId);

        report.setReportStatus(status);
        reportRepository.saveAndFlush(report);

        return "OK";
    }

    @GetMapping("/allreports")
    public List<Report> getAllReports() {
        return reportRepository.findAll();
    }

    @GetMapping("/services")
    public ApartmentMetric getApartmentMetric(@RequestParam("id") long telegramId) {
        User user = getTelegramUser(telegramId);
        return user.getOwnedHouse().getMetric();
    }

    @GetMapping("/reports")
    public List<Report> getReports(@RequestParam("id") long id) {
        House house = houseService.getHouseRepository().getReferenceById(id);
        return house.getReports();
    }

    @GetMapping("/user")
    public User getUser(@RequestParam("id") long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    @GetMapping("/tguser")
    public User getTelegramUser(@RequestParam("tgId") long telegramId) {
        return userRepository.getByTelegramId(telegramId);
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return userRepository.findAll();
    }

}
