package me.reclaite.bananosbackend.model.apartment;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Random;

@Entity
@Getter
@Setter
public class ApartmentMetric {

    @Deprecated
    private static Random random = new Random();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JsonBackReference
    private UserApartment apartment;

    private float gas;
    private float heating;
    private float water;
    private float electricity;

    // TODO: connect to real meters instead of random generation
    @Deprecated
    public static int getValue() {
        return random.nextInt(304040);
    }

}
