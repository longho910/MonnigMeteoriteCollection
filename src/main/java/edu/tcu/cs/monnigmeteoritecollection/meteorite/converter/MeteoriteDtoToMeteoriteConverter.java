package edu.tcu.cs.monnigmeteoritecollection.meteorite.converter;

import edu.tcu.cs.monnigmeteoritecollection.loan.LoanRepository;
import edu.tcu.cs.monnigmeteoritecollection.meteorite.Meteorite;
import edu.tcu.cs.monnigmeteoritecollection.meteorite.dto.MeteoriteDto;
import edu.tcu.cs.monnigmeteoritecollection.samplehistory.SampleHistory;
import edu.tcu.cs.monnigmeteoritecollection.system.exception.ObjectNotFoundException;
import edu.tcu.cs.monnigmeteoritecollection.samplehistory.SampleHistoryRepository;


import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class MeteoriteDtoToMeteoriteConverter implements Converter<MeteoriteDto, Meteorite> {

    private final SampleHistoryRepository sampleHistoryRepository;
    private final LoanRepository loanRepository;

    public MeteoriteDtoToMeteoriteConverter(SampleHistoryRepository sampleHistoryRepository, LoanRepository loanRepository) {
        this.sampleHistoryRepository = sampleHistoryRepository;
        this.loanRepository = loanRepository;
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
        
        meteorite.setSampleHistory(convertHistory(source.sampleHistories()));
        if (source.loan() != null) {
            meteorite.setLoan(this.loanRepository.findById(source.loan())
                    .orElseThrow(() -> new ObjectNotFoundException("Loan not found", String.valueOf(source.loan()))));
        } else {
            meteorite.setLoan(null);
        }


        return meteorite;
    }

    private List<SampleHistory> convertHistory(List<Long> source) {
        if (source == null) {
            return new ArrayList<>(); // Return empty list if source is null
        }
        List<SampleHistory> foundHistories = new ArrayList<>();
        for (Long elem : source) {
            foundHistories.add(this.sampleHistoryRepository.findById(elem)
                .orElseThrow(() -> new ObjectNotFoundException("Meteorite not found", String.valueOf(elem))));
        }
        return foundHistories;
    }
}
