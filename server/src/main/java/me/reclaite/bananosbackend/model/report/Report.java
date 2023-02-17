package me.reclaite.bananosbackend.model.report;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import me.reclaite.bananosbackend.model.user.User;

@Data
@Getter
@RequiredArgsConstructor
@Entity
public class Report {

    @Id
    private int id;

    private ReportType reportType;
    private String description;
    private int reporterId;

}
