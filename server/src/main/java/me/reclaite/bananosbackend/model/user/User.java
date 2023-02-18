package me.reclaite.bananosbackend.model.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity(name = "UserInfo")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    // todo: auth with the telegram bot
    private int telegramId;

    @ElementCollection
    private List<Long> ownedHouses;

}
