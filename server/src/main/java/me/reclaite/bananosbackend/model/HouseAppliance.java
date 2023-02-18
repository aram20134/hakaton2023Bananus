package me.reclaite.bananosbackend.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class HouseAppliance {

    private float gas;
    private float heating;
    private float water;
    private float electricity;

}
