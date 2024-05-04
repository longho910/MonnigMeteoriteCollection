package edu.tcu.cs.monnigmeteoritecollection.samplehistory.dto;

import io.micrometer.common.lang.Nullable;

public record SampleHistoryDto(
    @Nullable
    Long id,
    
    String date,
    String category,
    String notes,
    Long meteorite
) {

}
