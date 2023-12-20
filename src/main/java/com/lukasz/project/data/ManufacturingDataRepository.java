package com.lukasz.project.data;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ManufacturingDataRepository extends JpaRepository<ManufacturingData, Integer> {
    List<ManufacturingData> findByYear(Integer yearValue);
}
