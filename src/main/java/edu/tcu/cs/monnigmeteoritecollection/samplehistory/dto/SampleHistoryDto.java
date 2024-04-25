package edu.tcu.cs.monnigmeteoritecollection.samplehistory.dto;

import edu.tcu.cs.monnigmeteoritecollection.meteorite.dto.MeteoriteDto;

public record SampleHistoryDto(
    String date,
    String category,
    String notes,
    MeteoriteDto meteorite
) {

}
