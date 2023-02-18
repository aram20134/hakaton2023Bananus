package me.reclaite.bananosbackend.model.report;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Data
@Getter
@RequiredArgsConstructor
@Entity
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private ReportStatus reportStatus;

    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

    @PrePersist
    protected void onCreate() {
        creationDate = new Date();
    }

    private String description;
    private Long reporterId;

}
