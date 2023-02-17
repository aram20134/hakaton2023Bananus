package me.reclaite.bananosbackend.model.report;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import me.reclaite.bananosbackend.model.user.User;

@Data
@Getter
@RequiredArgsConstructor
public class Report {

    private final ReportType reportType;
    private final String description;
    private final User reporter;

}
