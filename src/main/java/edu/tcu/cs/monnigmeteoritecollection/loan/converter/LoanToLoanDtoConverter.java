package edu.tcu.cs.monnigmeteoritecollection.loan.converter;

import edu.tcu.cs.monnigmeteoritecollection.loan.Loan;
import edu.tcu.cs.monnigmeteoritecollection.loan.dto.LoanDto;
import edu.tcu.cs.monnigmeteoritecollection.meteorite.Meteorite;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class LoanToLoanDtoConverter implements Converter<Loan, LoanDto> {

    public LoanToLoanDtoConverter() {
    }

    @SuppressWarnings("null")
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

            source.isArchived(),

            convertMeteorites(source.getMeteorites()),
            source.getNotes(),
            source.getExtraFiles()
        );
        return loanDto;
    }

    private List<Long> convertMeteorites(List<Meteorite> source) {
        List<Long> meteoriteIdList = new ArrayList<>();

        for (Meteorite elem : source) {
            meteoriteIdList.add(elem.getId());
        }

        return meteoriteIdList;
    }
}
