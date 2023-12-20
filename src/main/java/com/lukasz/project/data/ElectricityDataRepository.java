package com.lukasz.project.data;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ElectricityDataRepository extends JpaRepository<ElectricityData, Integer> {
    List<ElectricityData> findByYear(Integer yearValue);
}
