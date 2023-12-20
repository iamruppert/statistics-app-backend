package com.lukasz.project.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/auth/airQualityData")
public class AirQualityController {
    private final AirQualityDataRepository airQualityDataRepository;

    @Autowired
    public AirQualityController(AirQualityDataRepository airQualityDataRepository) {
        this.airQualityDataRepository = airQualityDataRepository;
    }
    @GetMapping
    @CrossOrigin
    public List<AirQualityData> getAllData() {
        return airQualityDataRepository.findAll();
    }
    @GetMapping("/filterByYear")
    @CrossOrigin
    public List<AirQualityData> getDataByYear(@RequestParam Integer year) {
        return airQualityDataRepository.findByYearValue(year);
    }
}
