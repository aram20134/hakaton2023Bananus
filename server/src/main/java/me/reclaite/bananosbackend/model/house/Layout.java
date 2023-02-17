package me.reclaite.bananosbackend.model.house;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Entity
public class Layout {

    @Id
    private int id;

    private int area;
    private int roomsAmount;

}
