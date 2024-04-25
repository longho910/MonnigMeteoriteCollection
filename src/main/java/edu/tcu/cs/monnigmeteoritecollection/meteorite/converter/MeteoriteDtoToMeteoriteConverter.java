package edu.tcu.cs.monnigmeteoritecollection.meteorite.converter;

import edu.tcu.cs.monnigmeteoritecollection.loan.converter.LoanDtoToLoanConverter;
import edu.tcu.cs.monnigmeteoritecollection.meteorite.Meteorite;
import edu.tcu.cs.monnigmeteoritecollection.meteorite.dto.MeteoriteDto;
import edu.tcu.cs.monnigmeteoritecollection.samplehistory.converter.SampleHistoryDtoToSampleHistoryConverter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class MeteoriteDtoToMeteoriteConverter implements Converter<MeteoriteDto, Meteorite> {

    private final LoanDtoToLoanConverter loanDtoToLoanConverter;
    private final SampleHistoryDtoToSampleHistoryConverter sampleHistoryDtoToSampleHistoryConverter;

    public MeteoriteDtoToMeteoriteConverter(LoanDtoToLoanConverter loanDtoToLoanConverter, SampleHistoryDtoToSampleHistoryConverter sampleHistoryDtoToSampleHistoryConverter) {
        this.loanDtoToLoanConverter = loanDtoToLoanConverter;
        this.sampleHistoryDtoToSampleHistoryConverter = sampleHistoryDtoToSampleHistoryConverter;
    }

    @SuppressWarnings("null")
    @Override
    public Meteorite convert(MeteoriteDto source) {
        Meteorite meteorite = new Meteorite();
        meteorite.setId(source.id());
        meteorite.setName(source.name());
        meteorite.setMonnigNumber(source.monnigNumber());
        meteorite.setCountry(source.country());
        meteorite.set_class(source._class());
        meteorite.set_group(source.group());
        meteorite.setYearFound(source.yearFound());
        meteorite.setWeight(source.weight());

        meteorite.setHowFound(source.howFound());
        // convert sampleHistoryDtoList -> sampleHistoryList, and assign to meteorite
        meteorite.setSampleHistory(sampleHistoryDtoToSampleHistoryConverter.convertList(source.sampleHistory()));
        // convert loanDto -> loan and assign
        meteorite.setLoan(loanDtoToLoanConverter.convert(source.loan()));

        return meteorite;
    }

    public List<Meteorite> convertList(List<MeteoriteDto> source) {
        List<Meteorite> meteoriteList = new ArrayList<>();

        for (MeteoriteDto elem : source) {
            meteoriteList.add(convert(elem));
        }

        return meteoriteList;
    }
}
