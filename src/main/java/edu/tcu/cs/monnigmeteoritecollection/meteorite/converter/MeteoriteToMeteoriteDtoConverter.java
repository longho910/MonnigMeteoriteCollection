package edu.tcu.cs.monnigmeteoritecollection.meteorite.converter;

import edu.tcu.cs.monnigmeteoritecollection.loan.Loan;
import edu.tcu.cs.monnigmeteoritecollection.meteorite.Meteorite;
import edu.tcu.cs.monnigmeteoritecollection.meteorite.dto.MeteoriteDto;
import edu.tcu.cs.monnigmeteoritecollection.samplehistory.SampleHistory;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class MeteoriteToMeteoriteDtoConverter implements Converter<Meteorite, MeteoriteDto> {


    public MeteoriteToMeteoriteDtoConverter() {
    }

    @SuppressWarnings("null")
    @Override
    public MeteoriteDto convert(Meteorite source) {
        MeteoriteDto meteoriteDto = new MeteoriteDto(
            source.getId(),
            source.getName(),
            source.getMonnigNumber(),
            source.getCountry(),
            source.get_class(),
            source.get_group(),
            source.getYearFound(),
            source.getWeight(),
            source.getHowFound(),
            convertHistory(source.getSampleHistory()),
            convertLoan(source.getLoan())
        );

        return meteoriteDto;
    }

    private List<Long> convertHistory(List<SampleHistory> source) {
        List<Long> historyIdList = new ArrayList<>();

        for (SampleHistory elem : source) {
            historyIdList.add(elem.getId());
        }

        return historyIdList;
    }

    private Integer convertLoan(Loan source) {
        if (source == null) {
            return 0;
        }
        return source.getId();
    }
}
