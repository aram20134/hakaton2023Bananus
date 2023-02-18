package me.reclaite.bananosbackend.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import me.reclaite.bananosbackend.repository.CompanyRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Getter
public class CompanyService {

    private final CompanyRepository companyRepository;

}
