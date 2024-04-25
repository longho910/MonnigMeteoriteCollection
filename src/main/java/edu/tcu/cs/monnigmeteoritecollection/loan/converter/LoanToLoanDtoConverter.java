package edu.tcu.cs.monnigmeteoritecollection.loan.converter;

import edu.tcu.cs.monnigmeteoritecollection.loan.Loan;
import edu.tcu.cs.monnigmeteoritecollection.loan.dto.LoanDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class LoanToLoanDtoConverter implements Converter<Loan, LoanDto> {

    @Override
    public LoanDto convert(Loan source) {
        LoanDto loanDto = new LoanDto(
            source.getId(),
            source.getName(),
            source.getInstitution(),
            source.getEmail(),
            source.getPhone(),
            source.getAddress(),
            source.getLoanStartDate(),
            source.getLoanDueDate(),
            source.getNumberOfLoans(),
            source.getNotes(),
            source.getExtraFiles()
        );
        return loanDto;
    }
}
