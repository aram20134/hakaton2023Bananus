package me.reclaite.bananosbackend.model.report;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Data
@Getter
@RequiredArgsConstructor
@Entity
public class Report {

    @Id
    private Long id;

    private ReportType reportType;
    private String description;
    private Long reporterId;

}
