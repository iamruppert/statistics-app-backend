package com.lukasz.project.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/auth/combine")
public class CombineController {
    private final ElectricityDataRepository electricityDataRepository;
    private final IndustryDataRepository industryDataRepository;
    private final ManufacturingDataRepository manufacturingDataRepository;
    private final MiningDataRepository miningDataRepository;

    @Autowired
    public CombineController(ElectricityDataRepository electricityDataRepository, IndustryDataRepository industryDataRepository, ManufacturingDataRepository manufacturingDataRepository, MiningDataRepository miningDataRepository) {
        this.electricityDataRepository = electricityDataRepository;
        this.industryDataRepository = industryDataRepository;
        this.manufacturingDataRepository = manufacturingDataRepository;
        this.miningDataRepository = miningDataRepository;
    }

    @GetMapping("/ElectricityData")
    @CrossOrigin
    public List<ElectricityData> getAllElectricityData() {
        return electricityDataRepository.findAll();
    }

    @GetMapping("/ElectricityData/filterByYear")
    @CrossOrigin
    public List<ElectricityData> getElectricityDataByYear(@RequestParam Integer year) {
        return electricityDataRepository.findByYear(year);
    }

    @GetMapping("/IndustryData")
    @CrossOrigin
    public List<IndustryData> getAllIndustryData() {
        return industryDataRepository.findAll();
    }

    @GetMapping("/IndustryData/filterByYear")
    @CrossOrigin
    public List<IndustryData> getIndustryDataByYear(@RequestParam Integer year) {
        return industryDataRepository.findByYear(year);
    }

    @GetMapping("/ManufacturingData")
    @CrossOrigin
    public List<ManufacturingData> getAllManufacturingData() {
        return manufacturingDataRepository.findAll();
    }

    @GetMapping("/ManufacturingData/filterByYear")
    @CrossOrigin
    public List<ManufacturingData> getManufacturingDataByYear(@RequestParam Integer year) {
        return manufacturingDataRepository.findByYear(year);
    }

    @GetMapping("/MiningData")
    @CrossOrigin
    public List<MiningData> getAllMiningData() {
        return miningDataRepository.findAll();
    }

    @GetMapping("/MiningData/filterByYear")
    @CrossOrigin
    public List<MiningData> getMiningDataByYear(@RequestParam Integer year) {
        return miningDataRepository.findByYear(year);
    }
}
