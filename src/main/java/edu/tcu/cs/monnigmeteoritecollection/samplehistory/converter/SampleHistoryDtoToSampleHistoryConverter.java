package edu.tcu.cs.monnigmeteoritecollection.samplehistory.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import edu.tcu.cs.monnigmeteoritecollection.meteorite.MeteoriteRepository;
import edu.tcu.cs.monnigmeteoritecollection.samplehistory.SampleHistory;
import edu.tcu.cs.monnigmeteoritecollection.samplehistory.dto.SampleHistoryDto;
import edu.tcu.cs.monnigmeteoritecollection.system.exception.ObjectNotFoundException;

@Component
public class SampleHistoryDtoToSampleHistoryConverter implements Converter<SampleHistoryDto, SampleHistory> {

    private final MeteoriteRepository meteoriteRepository;

    public SampleHistoryDtoToSampleHistoryConverter(MeteoriteRepository meteoriteRepository) {
        this.meteoriteRepository = meteoriteRepository;
    }

    @SuppressWarnings("null")
    @Override
    public SampleHistory convert(SampleHistoryDto source) {
        SampleHistory sampleHistory = new SampleHistory();

        sampleHistory.setId(source.id());

        sampleHistory.setDate(source.date());
        sampleHistory.setCategory(source.category());
        sampleHistory.setNotes(source.notes());
        
        if (source.meteorite() != null) {
            sampleHistory.setMeteorite(this.meteoriteRepository.findById(source.meteorite())
                .orElseThrow(() -> new ObjectNotFoundException("Meteorite not found", String.valueOf(source.meteorite()))));
        }
        
        return sampleHistory;
    }
}
