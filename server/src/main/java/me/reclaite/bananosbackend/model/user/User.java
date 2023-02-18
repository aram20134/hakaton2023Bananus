package me.reclaite.bananosbackend.model.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import me.reclaite.bananosbackend.model.apartment.UserApartment;

@Getter
@Setter
@Entity(name = "UserInfo")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private Long telegramId;

    private String telegramUsername;

    @OneToOne(cascade = CascadeType.ALL)
    private UserApartment ownedHouse;

}
