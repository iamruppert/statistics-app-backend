package com.lukasz.project.data;

import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface MiningDataRepository extends JpaRepository<MiningData, BigDecimal> {
    List<MiningData> findByYear(Integer yearValue);
}
