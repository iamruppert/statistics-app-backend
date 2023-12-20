package com.lukasz.project.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/auth/electricityData")
public class ElectricityController {
    private final ElectricityDataRepository electricityDataRepository;

    @Autowired
    public ElectricityController(ElectricityDataRepository electricityDataRepository) {
        this.electricityDataRepository = electricityDataRepository;
    }
    @GetMapping
    @CrossOrigin
    public List<ElectricityData> getAllData() {
        return electricityDataRepository.findAll();
    }
    @GetMapping("/filterByYear")
    @CrossOrigin
    public List<ElectricityData> getDataByYear(@RequestParam Integer year) {
        return electricityDataRepository.findByYear(year);
    }
}
