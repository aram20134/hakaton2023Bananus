package me.reclaite.bananosbackend.repository;

import me.reclaite.bananosbackend.model.report.Report;
import me.reclaite.bananosbackend.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report, Long> {

}
