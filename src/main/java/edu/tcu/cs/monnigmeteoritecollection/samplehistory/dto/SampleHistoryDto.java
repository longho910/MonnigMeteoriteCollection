package edu.tcu.cs.monnigmeteoritecollection.samplehistory.dto;


public record SampleHistoryDto(
    String date,
    String category,
    String notes,
    Long meteorite
) {

}
