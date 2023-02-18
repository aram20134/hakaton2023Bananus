package me.reclaite.bananosbackend.model.house;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum HouseType {

    COMPLEX("Жилой комплекс"),
    BUSINESS("Бизнес-центр"),
    MALL("Апарт-отель"),
    HOTEL("Отель");

    private final String name;

}
