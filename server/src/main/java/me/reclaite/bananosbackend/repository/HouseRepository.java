package me.reclaite.bananosbackend.repository;

import me.reclaite.bananosbackend.model.house.House;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HouseRepository extends JpaRepository<House, Integer> {
}
