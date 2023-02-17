package me.reclaite.bananosbackend.model;

import lombok.Data;
import me.reclaite.bananosbackend.model.house.HouseType;
import me.reclaite.bananosbackend.model.house.Planner;

@Data
public class HouseAction {

    private final int id;
    private final String houseName;
    private final HouseType houseType;

    private final float price;
    private final Planner planner;

}
