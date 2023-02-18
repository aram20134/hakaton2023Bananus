package me.reclaite.bananosbackend.model.apartment;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import me.reclaite.bananosbackend.model.house.House;
import me.reclaite.bananosbackend.model.house.Layout;

@Entity
@Getter
@Setter
public class UserApartment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    private House house;

    private int apartment;

    @OneToOne(cascade = CascadeType.ALL)
    private Layout layout;

    @OneToOne(cascade = CascadeType.ALL)
    private ApartmentMetric metric;

}
