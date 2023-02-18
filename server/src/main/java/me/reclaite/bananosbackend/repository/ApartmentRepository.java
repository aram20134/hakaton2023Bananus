package me.reclaite.bananosbackend.repository;

import me.reclaite.bananosbackend.model.apartment.UserApartment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApartmentRepository extends JpaRepository<UserApartment, Long> {
}
