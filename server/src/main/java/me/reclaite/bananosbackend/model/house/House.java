package me.reclaite.bananosbackend.model.house;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import me.reclaite.bananosbackend.model.company.Company;
import me.reclaite.bananosbackend.model.user.User;

@Data
@Getter
@Setter
@Entity
public class House {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    private Company owner;

    private String houseName;
    private String address;
    private HouseType houseType;

    @OneToOne(cascade = CascadeType.ALL)
    private Layout layout;

}
