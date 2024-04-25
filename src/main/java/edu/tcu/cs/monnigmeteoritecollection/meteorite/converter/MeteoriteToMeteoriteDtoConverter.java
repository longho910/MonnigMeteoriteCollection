package edu.tcu.cs.monnigmeteoritecollection.meteorite.converter;

import edu.tcu.cs.monnigmeteoritecollection.loan.Loan;
import edu.tcu.cs.monnigmeteoritecollection.loan.dto.LoanDto;
import edu.tcu.cs.monnigmeteoritecollection.meteorite.Meteorite;
import edu.tcu.cs.monnigmeteoritecollection.meteorite.dto.MeteoriteDto;
import edu.tcu.cs.monnigmeteoritecollection.samplehistory.SampleHistory;
import edu.tcu.cs.monnigmeteoritecollection.samplehistory.dto.SampleHistoryDto;

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
            source.getSampleHistory() != null
                ? convertHistoryList(source.getSampleHistory()) : null,
            source.getLoan() != null
                ? convertLoan(source.getLoan()) : null
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

    public LoanDto convertLoan(Loan source) {
        LoanDto loanDto = new LoanDto(
            source.getId(),
            source.getName(),
            source.getInstitution(),
            source.getEmail(),
            source.getPhone(),
            source.getAddress(),
            source.getLoanStartDate(),
            source.getLoanDueDate(),

            source.isArchived(),

            source.getMeteorites() != null
                ? convertList(source.getMeteorites()) : null,
            source.getNotes(),
            source.getExtraFiles()
        );
        return loanDto;
    }

    public SampleHistoryDto convertHistory(SampleHistory source) {
        SampleHistoryDto sampleHistoryDto = new SampleHistoryDto(
            source.getDate(),
            source.getCategory(),
            source.getNotes(),
            
            source.getMeteorite() != null
                ? convert(source.getMeteorite()) : null
        );
        return sampleHistoryDto;
    }

    public List<SampleHistoryDto> convertHistoryList(List<SampleHistory> source) {
        List<SampleHistoryDto> sampleHistoryDtoList = new ArrayList<>();

        for (SampleHistory elem : source) {
            sampleHistoryDtoList.add(convertHistory(elem));
        }

        return sampleHistoryDtoList;
    }
}
