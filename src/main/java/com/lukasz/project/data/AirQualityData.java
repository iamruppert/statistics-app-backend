package com.lukasz.project.data;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name= "air_quality")
public class AirQualityData {
    @Id
    @GeneratedValue()
    @Column(name="_id")
    private Integer id;

    @Column(name = "_rok")
    private Integer yearValue;

    @Column(name = "dwutlenek_siarki")
    private Long sulfurDioxide;

    @Column(name = "tlenki_azotu")
    private Long nitrogenOxides;

    @Column(name = "dwutlenek_wegla")
    private Long carbonDioxide;

    @Column(name = "tlenek_wegla")
    private Long carbonMonoxide;

    @Column(name = "lotne_zwiazki_organiczne_bez_metanu")
    private Long nonMethaneVolatileOrganicCompounds;

    @Column(name = "zrodla_antropogenne")
    private Long anthropogenicSources;

    @Column(name = "natura")
    private Long nature;

    @Column(name = "amoniak")
    private Long ammonia;

    @Column(name = "czastki_stale")
    private Long particulateMatter;

}