package me.reclaite.bananosbackend.model.user;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

import java.util.List;

@Entity
@Getter
public class User {

    @Id
    private int id;
    // todo: auth with the telegram bot
    private int telegramId;

    @ElementCollection
    private List<Integer> ownedHouses;

}
