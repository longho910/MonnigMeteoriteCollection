package edu.tcu.cs.monnigmeteoritecollection.loan.converter;

import java.util.ArrayList;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import edu.tcu.cs.monnigmeteoritecollection.loan.Loan;
import edu.tcu.cs.monnigmeteoritecollection.loan.dto.LoanDto;
import edu.tcu.cs.monnigmeteoritecollection.meteorite.converter.MeteoriteDtoToMeteoriteConverter;

@Component
public class LoanDtoToLoanConverter implements Converter<LoanDto, Loan> {

    private final MeteoriteDtoToMeteoriteConverter meteoriteDtoToMeteoriteConverter;

    public LoanDtoToLoanConverter(MeteoriteDtoToMeteoriteConverter meteoriteDtoToMeteoriteConverter) {
        this.meteoriteDtoToMeteoriteConverter = meteoriteDtoToMeteoriteConverter;
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

        // set meteorites
        loan.setNotes(source.notes());
        loan.setExtraFiles(source.extraFiles());

        return loan;
    }

}
