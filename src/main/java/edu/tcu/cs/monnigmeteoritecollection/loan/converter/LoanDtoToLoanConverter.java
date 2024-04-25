package edu.tcu.cs.monnigmeteoritecollection.loan.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import edu.tcu.cs.monnigmeteoritecollection.loan.Loan;
import edu.tcu.cs.monnigmeteoritecollection.loan.dto.LoanDto;

@Component
public class LoanDtoToLoanConverter implements Converter<LoanDto, Loan> {

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

        return loan;
    }

}
