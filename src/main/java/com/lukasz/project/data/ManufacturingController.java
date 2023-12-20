package com.lukasz.project.data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/auth/manufacturingData")
public class ManufacturingController {
    private final ManufacturingDataRepository manufacturingDataRepository ;

    @Autowired
    public ManufacturingController(ManufacturingDataRepository manufacturingDataRepository) {
        this.manufacturingDataRepository = manufacturingDataRepository;
    }
    @GetMapping
    @CrossOrigin
    public List<ManufacturingData> getAllData() {
        return manufacturingDataRepository.findAll();
    }
    @GetMapping("/filterByYear")
    @CrossOrigin
    public List<ManufacturingData> getDataByYear(@RequestParam Integer year) {
        return manufacturingDataRepository.findByYear(year);
    }
}