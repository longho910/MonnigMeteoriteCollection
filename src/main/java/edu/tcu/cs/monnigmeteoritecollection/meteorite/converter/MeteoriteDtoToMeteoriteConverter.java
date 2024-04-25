package edu.tcu.cs.monnigmeteoritecollection.meteorite.converter;

import edu.tcu.cs.monnigmeteoritecollection.loan.converter.LoanDtoToLoanConverter;
import edu.tcu.cs.monnigmeteoritecollection.meteorite.Meteorite;
import edu.tcu.cs.monnigmeteoritecollection.meteorite.dto.MeteoriteDto;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class MeteoriteDtoToMeteoriteConverter implements Converter<MeteoriteDto, Meteorite> {

    private final LoanDtoToLoanConverter loanDtoToLoanConverter;

    public MeteoriteDtoToMeteoriteConverter(LoanDtoToLoanConverter loanDtoToLoanConverter) {
        this.loanDtoToLoanConverter = loanDtoToLoanConverter;
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
        // meteorite.setSampleHistory(sampleHistoryDtoToSampleHistoryConverter.convert(source.sampleHistory()));
        meteorite.setLoan(loanDtoToLoanConverter.convert(source.loan()));

        return meteorite;
    }
}
