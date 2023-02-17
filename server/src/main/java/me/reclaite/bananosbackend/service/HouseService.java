package me.reclaite.bananosbackend.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import me.reclaite.bananosbackend.model.house.House;
import me.reclaite.bananosbackend.repository.CompanyRepository;
import me.reclaite.bananosbackend.repository.HouseRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@RequiredArgsConstructor
public class HouseService {

    @Getter
    private final HouseRepository houseRepository;

    public void createHouse(House house) {
        houseRepository.saveAndFlush(house);
    }

}
