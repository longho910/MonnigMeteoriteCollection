package edu.tcu.cs.monnigmeteoritecollection.meteorite.converter;

import edu.tcu.cs.monnigmeteoritecollection.loan.converter.LoanToLoanDtoConverter;
import edu.tcu.cs.monnigmeteoritecollection.meteorite.Meteorite;
import edu.tcu.cs.monnigmeteoritecollection.meteorite.dto.MeteoriteDto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class MeteoriteToMeteoriteDtoConverter implements Converter<Meteorite, MeteoriteDto> {
    private final LoanToLoanDtoConverter loanToLoanDtoConverter;

    public MeteoriteToMeteoriteDtoConverter(LoanToLoanDtoConverter loanToLoanDtoConverter) {
        this.loanToLoanDtoConverter = loanToLoanDtoConverter;
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
            // source.getSampleHistory() != null
            //     ? this.sampleHistoryToSampleHistoryDtoConverter.convert(source.getSampleHistory()) : null,
            source.getLoan() != null
                ? this.loanToLoanDtoConverter.convert(source.getLoan()) : null
        );

        return meteoriteDto;
    }

    public List<MeteoriteDto> convertList(List<Meteorite> source) {
        List<MeteoriteDto> meteoriteDtoList = new ArrayList<>();

        for (Meteorite elem : source) {
            meteoriteDtoList.add(convert(elem));
        }

        return meteoriteDtoList;
    }
}
