package me.reclaite.bananosbackend.model;

import lombok.Data;
import me.reclaite.bananosbackend.model.company.Company;
import me.reclaite.bananosbackend.model.house.HouseType;
import me.reclaite.bananosbackend.model.house.Layout;

@Data
public class HouseData {

    private final Long id;

    private final Company ownerCompany;

    private final String houseName;
    private final String houseAddress;
    private final HouseType houseType;

    private final int apartment;

    private final Layout layout;
    private final float price;

}
