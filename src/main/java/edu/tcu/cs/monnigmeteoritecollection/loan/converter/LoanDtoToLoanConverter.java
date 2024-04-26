package edu.tcu.cs.monnigmeteoritecollection.loan.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import edu.tcu.cs.monnigmeteoritecollection.loan.Loan;
import edu.tcu.cs.monnigmeteoritecollection.loan.dto.LoanDto;
import edu.tcu.cs.monnigmeteoritecollection.meteorite.Meteorite;
import edu.tcu.cs.monnigmeteoritecollection.meteorite.MeteoriteRepository;
import edu.tcu.cs.monnigmeteoritecollection.system.exception.ObjectNotFoundException;

@Component
public class LoanDtoToLoanConverter implements Converter<LoanDto, Loan> {

    private final MeteoriteRepository meteoriteRepository;

    public LoanDtoToLoanConverter(MeteoriteRepository meteoriteRepository) {
        this.meteoriteRepository = meteoriteRepository;
    }

    @SuppressWarnings("null")
    @Override
    public Loan convert(LoanDto source) {
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

        loan.setMeteorites(convertMeteorites(source.meteorites()));

        loan.setNotes(source.notes());
        loan.setExtraFiles(source.extraFiles());

        return loan;
    }

    private List<Meteorite> convertMeteorites(List<Long> source) {
        List<Meteorite> foundMeteorites = new ArrayList<>();
        for (Long elem : source) {
            foundMeteorites.add(this.meteoriteRepository.findById(elem)
                .orElseThrow(() -> new ObjectNotFoundException("Meteorite not found", String.valueOf(elem))));
        }
        return foundMeteorites;
    }

}
