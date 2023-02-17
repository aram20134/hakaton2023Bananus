package me.reclaite.bananosbackend.model.house;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum HouseType {

    APARTMENT("Квартира"),
    HOUSE("Частный дом"),
    PENTHOUSE("Пентхаус");

    private final String name;

}
