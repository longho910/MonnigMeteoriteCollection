package edu.tcu.cs.monnigmeteoritecollection.samplehistory.converter;

import edu.tcu.cs.monnigmeteoritecollection.samplehistory.SampleHistory;
import edu.tcu.cs.monnigmeteoritecollection.samplehistory.dto.SampleHistoryDto;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class SampleHistoryToSampleHistoryDtoConverter implements Converter<SampleHistory, SampleHistoryDto> {

    public SampleHistoryToSampleHistoryDtoConverter() {
    }

    @SuppressWarnings("null")
    @Override
    public SampleHistoryDto convert(SampleHistory source) {
        SampleHistoryDto sampleHistoryDto = new SampleHistoryDto(
            source.getId(),
            source.getDate(),
            source.getCategory(),
            source.getNotes(),
            source.getMeteoriteId()
        );
        return sampleHistoryDto;
    }
}