package me.reclaite.bananosbackend.repository;

import me.reclaite.bananosbackend.model.house.House;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface HouseRepository extends JpaRepository<House, Long> {

    @Query("SELECT house FROM House house WHERE house.address = :address")
    House getHouseByAddress(String address);

    @Query("SELECT house FROM House house WHERE house.houseName = :name")
    House getHouseByName(String name);

}
