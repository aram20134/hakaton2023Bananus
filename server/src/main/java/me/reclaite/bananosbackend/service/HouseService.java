package me.reclaite.bananosbackend.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import me.reclaite.bananosbackend.repository.HouseRepository;
import me.reclaite.bananosbackend.repository.LayoutRepository;
import me.reclaite.bananosbackend.repository.ReportRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Getter
public class HouseService {

    private final HouseRepository houseRepository;

    private final LayoutRepository layoutRepository;

    private final ReportRepository reportRepository;

}
