package me.reclaite.bananosbackend.model.apartment;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonBackReference
    private House house;

    private int apartment;

    @OneToOne(cascade = CascadeType.ALL)
    private Layout layout;

    @OneToOne(cascade = CascadeType.ALL)
    @JsonManagedReference
    private ApartmentMetric metric;

}
