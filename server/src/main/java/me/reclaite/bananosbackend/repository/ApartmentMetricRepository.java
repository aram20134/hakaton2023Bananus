package me.reclaite.bananosbackend.repository;

import me.reclaite.bananosbackend.model.apartment.ApartmentMetric;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApartmentMetricRepository extends JpaRepository<ApartmentMetric, Long> {
}
