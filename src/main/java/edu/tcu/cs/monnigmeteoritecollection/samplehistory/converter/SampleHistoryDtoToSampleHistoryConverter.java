package edu.tcu.cs.monnigmeteoritecollection.samplehistory.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import edu.tcu.cs.monnigmeteoritecollection.samplehistory.SampleHistory;
import edu.tcu.cs.monnigmeteoritecollection.samplehistory.dto.SampleHistoryDto;

@Component
public class SampleHistoryDtoToSampleHistoryConverter implements Converter<SampleHistoryDto, SampleHistory> {

    @SuppressWarnings("null")
    @Override
    public SampleHistory convert(SampleHistoryDto source) {
        SampleHistory sampleHistory = new SampleHistory(
            source.date(),
            source.category(),
            source.notes()
        );

        return sampleHistory;
    }

}
