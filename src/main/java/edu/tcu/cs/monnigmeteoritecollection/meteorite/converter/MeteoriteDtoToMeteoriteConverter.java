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
public class MeteoriteDtoToMeteoriteConverter implements Converter<MeteoriteDto, Meteorite> {

    public MeteoriteDtoToMeteoriteConverter() {
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
        meteorite.setSampleHistory(convertHistoryList(source.sampleHistory()));
        // convert loanDto -> loan and assign
        meteorite.setLoan(convertLoan(source.loan()));

        return meteorite;
    }

    public List<Meteorite> convertList(List<MeteoriteDto> source) {
        List<Meteorite> meteoriteList = new ArrayList<>();

        for (MeteoriteDto elem : source) {
            meteoriteList.add(convert(elem));
        }

        return meteoriteList;
    }

    public Loan convertLoan(LoanDto source) {
        Loan loan = new Loan();
        
        loan.setId(source.id());
        loan.setName(source.name());
        loan.setInstitution(source.institution());
        loan.setEmail(source.email());
        loan.setPhone(source.phone());
        loan.setAddress(source.address());
        loan.setLoanStartDate(source.loanStartDate());
        loan.setLoanDueDate(source.loanDueDate());

        loan.setArchived(source.isArchived());

        loan.setMeteorites(
            convertList(source.meteoriteDtoList())
        );

        loan.setNotes(source.notes());
        loan.setExtraFiles(source.extraFiles());

        return loan;
    }

    public SampleHistory convertHistory(SampleHistoryDto source) {
        SampleHistory sampleHistory = new SampleHistory();

        sampleHistory.setDate(source.date());
        sampleHistory.setCategory(source.category());
        sampleHistory.setNotes(source.notes());
        
        sampleHistory.setMeteorite(convert(source.meteorite()));

        return sampleHistory;
    }

    public List<SampleHistory> convertHistoryList(List<SampleHistoryDto> source) {
        List<SampleHistory> sampleHistoryList = new ArrayList<>();

        for (SampleHistoryDto elem : source) {
            sampleHistoryList.add(convertHistory(elem));
        }

        return sampleHistoryList;
    }
}
