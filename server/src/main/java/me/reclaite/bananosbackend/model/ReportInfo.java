package me.reclaite.bananosbackend.model;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class ReportInfo {

    private final Long telegramId;
    private final String description;
}
