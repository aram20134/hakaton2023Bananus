package me.reclaite.bananosbackend.controller;

import lombok.RequiredArgsConstructor;
import me.reclaite.bananosbackend.model.report.Report;
import me.reclaite.bananosbackend.model.report.ReportType;
import me.reclaite.bananosbackend.model.user.User;
import me.reclaite.bananosbackend.repository.UserRepository;
import me.reclaite.bananosbackend.service.HouseService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    private final HouseService houseService;

    @PostMapping("/report")
    public Report reportProblem(@RequestParam("userId") int userId,
                                @RequestParam ReportType reportType,
                                @RequestParam String description) {
        User user = userRepository.getById(userId);

        Report report = new Report();
        report.setReporterId(user.getId());
        report.setReportType(reportType);
        report.setDescription(description);

        houseService.getReportRepository().saveAndFlush(report);

        return report;
    }

}
