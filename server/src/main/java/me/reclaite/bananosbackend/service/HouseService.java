package me.reclaite.bananosbackend.service;

import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import me.reclaite.bananosbackend.model.house.Layout;
import me.reclaite.bananosbackend.repository.HouseRepository;
import me.reclaite.bananosbackend.repository.LayoutRepository;
import me.reclaite.bananosbackend.repository.ReportRepository;
import me.reclaite.bananosbackend.util.ImageUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Getter
public class HouseService {

    private final HouseRepository houseRepository;

    private final LayoutRepository layoutRepository;

    private final ReportRepository reportRepository;

}
