package me.reclaite.bananosbackend.model;

import lombok.Data;
import me.reclaite.bananosbackend.model.house.HouseType;

@Data
public class HouseAction {

    private final int id;
    private final String houseName;
    private final String houseAddress;
    private final HouseType houseType;

    private final int layoutId;
    private final float price;

}
