package me.reclaite.bananosbackend.model.apartment;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.Random;

@Entity
@Getter
@Setter
public class ApartmentMetric {

    private static Random random = new Random();

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private float gas;
    private float heating;
    private float water;

    private float electricity;

    public static int getValue() {
        return random.nextInt(304040);
    }

}
