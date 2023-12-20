package com.lukasz.project.data;

import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface IndustryDataRepository extends JpaRepository<IndustryData, BigDecimal> {
    List<IndustryData> findByYear(Integer yearValue);
}
