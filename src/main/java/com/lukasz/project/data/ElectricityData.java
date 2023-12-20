package com.lukasz.project.data;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "electricity")
public class ElectricityData {
    @Id
    @GeneratedValue
    @Column(name = "_id")
    private Integer id;

    @Column(name = "_rok")
    private Integer year;

    @Column(name = "produkcja_sprzedana")
    private BigDecimal produkcjaSprzedana;

    @Column(name = "przecietne_zatrudnienie")
    private BigDecimal przecietneZatrudnienie;

    @Column(name = "wydajnosc_pracy")
    private BigDecimal wydajnoscPracy;

    @Column(name = "przecietne_wynagrodzenia")
    private BigDecimal przecietneWynagrodzenia;
}
