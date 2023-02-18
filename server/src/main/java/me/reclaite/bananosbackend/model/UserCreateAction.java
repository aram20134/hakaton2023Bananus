package me.reclaite.bananosbackend.model;

import lombok.Data;

@Data
public class UserCreateAction {

    private final int telegramId;

    private final String complex;

}
