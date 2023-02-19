package me.reclaite.bananosbackend.model;

import lombok.Data;

@Data
public class UserCreateAction {

    private final long telegramId;
    private final String telegramUsername;

    private final String complex;
    private final int apartment;

}
