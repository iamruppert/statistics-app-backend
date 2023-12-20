package com.lukasz.project.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/auth/industryController")
public class IndustryController {
    private final IndustryDataRepository industryDataRepository ;

    @Autowired
    public IndustryController(IndustryDataRepository industryDataRepository) {
        this.industryDataRepository = industryDataRepository;
    }
    @GetMapping
    @CrossOrigin
    public List<IndustryData> getAllData() {
        return industryDataRepository.findAll();
    }
    @GetMapping("/filterByYear")
    @CrossOrigin
    public List<IndustryData> getDataByYear(@RequestParam Integer year) {
        return industryDataRepository.findByYear(year);
    }
}