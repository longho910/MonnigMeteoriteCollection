package edu.tcu.cs.monnigmeteoritecollection.samplehistory.converter;

import edu.tcu.cs.monnigmeteoritecollection.meteorite.converter.MeteoriteToMeteoriteDtoConverter;
import edu.tcu.cs.monnigmeteoritecollection.samplehistory.SampleHistory;
import edu.tcu.cs.monnigmeteoritecollection.samplehistory.dto.SampleHistoryDto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class SampleHistoryToSampleHistoryDtoConverter implements Converter<SampleHistory, SampleHistoryDto> {

    private final MeteoriteToMeteoriteDtoConverter meteoriteToMeteoriteDtoConverter;

    public SampleHistoryToSampleHistoryDtoConverter(MeteoriteToMeteoriteDtoConverter meteoriteToMeteoriteDtoConverter) {
        this.meteoriteToMeteoriteDtoConverter = meteoriteToMeteoriteDtoConverter;
    }

    @SuppressWarnings("null")
    @Override
    public SampleHistoryDto convert(SampleHistory source) {
        SampleHistoryDto sampleHistoryDto = new SampleHistoryDto(
            source.getDate(),
            source.getCategory(),
            source.getNotes(),
            this.meteoriteToMeteoriteDtoConverter.convert(source.getMeteorite())
        );
        return sampleHistoryDto;
    }

    public List<SampleHistoryDto> convertList(List<SampleHistory> source) {
        List<SampleHistoryDto> sampleHistoryDtoList = new ArrayList<>();

        for (SampleHistory elem : source) {
            sampleHistoryDtoList.add(convert(elem));
        }

        return sampleHistoryDtoList;
    }
}