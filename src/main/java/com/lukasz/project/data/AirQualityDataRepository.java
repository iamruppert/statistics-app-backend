package com.lukasz.project.data;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AirQualityDataRepository extends JpaRepository<AirQualityData, Long> {
    List<AirQualityData> findByYearValue(Integer yearValue);
}
