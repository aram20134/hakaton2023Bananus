package me.reclaite.bananosbackend.model.house;

import jakarta.annotation.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Entity
public class House {

    @Id
    private int id;
    private String houseName;
    private String address;
    private HouseType houseType;

    private int plannerId;

    @Nullable
    private int apartment;
    private float price;

}
