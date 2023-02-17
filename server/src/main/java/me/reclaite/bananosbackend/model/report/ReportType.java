package me.reclaite.bananosbackend.model.report;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ReportType {

    LIGHTS_DOWN("Отключение света"),
    PIPE_BREAK("Прорыв трубопровода"),
    HEATING_DOWN("Отключение отопления");

    private final String title;

}
