package edu.tcu.cs.monnigmeteoritecollection.samplehistory.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import edu.tcu.cs.monnigmeteoritecollection.meteorite.converter.MeteoriteDtoToMeteoriteConverter;
import edu.tcu.cs.monnigmeteoritecollection.samplehistory.SampleHistory;
import edu.tcu.cs.monnigmeteoritecollection.samplehistory.dto.SampleHistoryDto;

@Component
public class SampleHistoryDtoToSampleHistoryConverter implements Converter<SampleHistoryDto, SampleHistory> {

    private final MeteoriteDtoToMeteoriteConverter meteoriteDtoToMeteoriteConverter;

    public SampleHistoryDtoToSampleHistoryConverter(MeteoriteDtoToMeteoriteConverter meteoriteDtoToMeteoriteConverter) {
        this.meteoriteDtoToMeteoriteConverter = meteoriteDtoToMeteoriteConverter;
    }

    @SuppressWarnings("null")
    @Override
    public SampleHistory convert(SampleHistoryDto source) {
        SampleHistory sampleHistory = new SampleHistory();

        sampleHistory.setDate(source.date());
        sampleHistory.setCategory(source.category());
        sampleHistory.setNotes(source.notes());
        sampleHistory.setMeteorite(meteoriteDtoToMeteoriteConverter.convert(source.meteorite()));

        return sampleHistory;
    }

}
