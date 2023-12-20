package com.lukasz.project.data;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/auth/miningData")
public class MiningController {
    private final MiningDataRepository miningDataRepository ;

    public MiningController(MiningDataRepository manufacturingDataRepository) {
        this.miningDataRepository = manufacturingDataRepository;
    }
    @GetMapping
    @CrossOrigin
    public List<MiningData> getAllData() {
        return miningDataRepository.findAll();
    }
    @GetMapping("/filterByYear")
    @CrossOrigin
    public List<MiningData> getDataByYear(@RequestParam Integer year) {
        return miningDataRepository.findByYear(year);
    }
}
