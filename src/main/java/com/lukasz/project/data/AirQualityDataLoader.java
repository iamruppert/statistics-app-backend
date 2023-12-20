package com.lukasz.project.data;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;

@Component
public class AirQualityDataLoader {
    private final AirQualityDataRepository airQualityDataRepository;

    @Autowired
    public AirQualityDataLoader(AirQualityDataRepository airQualityDataRepository) {
        this.airQualityDataRepository = airQualityDataRepository;
    }

    @PostConstruct
    public void importData() {
        try {
            InputStream inputStream = getClass().getResourceAsStream("/air_quality.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(inputStream);
            NodeList yearNodes = document.getElementsByTagName("year");

            for (int i = 0; i < yearNodes.getLength(); i++) {
                Element yearElement = (Element) yearNodes.item(i);
                AirQualityData data = new AirQualityData();
                data.setYearValue(Integer.parseInt(getElementText(yearElement, "year_value")));
                data.setSulfurDioxide(Long.valueOf(getElementText(yearElement, "sulfur_dioxide")));
                data.setNitrogenOxides(Long.valueOf(getElementText(yearElement, "nitrogen_oxides")));
                data.setCarbonDioxide(Long.valueOf(getElementText(yearElement, "carbon_dioxide")));
                data.setCarbonMonoxide(Long.valueOf(getElementText(yearElement, "carbon_monoxide")));
                data.setNonMethaneVolatileOrganicCompounds(Long.valueOf(getElementText(yearElement, "non_methane_volatile_organic_compounds")));
                data.setAnthropogenicSources(Long.valueOf(getElementText(yearElement, "anthropogenic_sources")));
                data.setNature(Long.valueOf(getElementText(yearElement, "nature")));
                data.setAmmonia(Long.valueOf(getElementText(yearElement, "ammonia")));
                data.setParticulateMatter(Long.valueOf(getElementText(yearElement, "particulate_matter")));
                airQualityDataRepository.save(data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getElementText(Element element, String tagName) {
        return element.getElementsByTagName(tagName).item(0).getTextContent();
    }
}
