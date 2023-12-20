package com.lukasz.project.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import java.io.InputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;

@Component
public class IndustryDataLoader {

    private final IndustryDataRepository industryDataRepository;

    @Autowired
    public IndustryDataLoader(IndustryDataRepository industryDataRepository) {
        this.industryDataRepository = industryDataRepository;
    }

    @PostConstruct
    public void importData() {
        try {
            InputStream file = getClass().getResourceAsStream("/industryv2.json");
            // Odczyt pliku JSON
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Map<String, String>> jsonData = objectMapper.readValue(file, Map.class);

            // Przetwarzanie danych i zapis do bazy danych
            for (Map.Entry<String, Map<String, String>> entry : jsonData.entrySet()) {
                String year = entry.getKey();
                Map<String, String> data = entry.getValue();

                IndustryData industryData = IndustryData.builder()
                        .year(Integer.valueOf(year))
                        .produkcjaSprzedana(parseBigDecimalValue(data.get("Produkcja sprzedana w cenach na 2020r. w mln zł")))
                        .przecietneZatrudnienie(parseBigDecimalValue(data.get("Przeciętne zatrudnienie w tys.")))
                        .wydajnoscPracy(parseBigDecimalValue(data.get("Wydajność pracy w cenach na 2020r. w tys. zł")))
                        .przecietneWynagrodzenia(parseBigDecimalValue(data.get("Przeciętne miesięczne wynagrodzenia brutto w cenach na 2020r. w zł")))
                        .build();

                industryDataRepository.save(industryData);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private BigDecimal parseBigDecimalValue(String value) {
        if (value != null && !value.isEmpty()) {
            try {
                // Zamiana na typ BigDecimal
                return new BigDecimal(value.replace(",", "."));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
