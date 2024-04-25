package edu.tcu.cs.monnigmeteoritecollection.loan.converter;

import edu.tcu.cs.monnigmeteoritecollection.loan.Loan;
import edu.tcu.cs.monnigmeteoritecollection.loan.dto.LoanDto;
import edu.tcu.cs.monnigmeteoritecollection.meteorite.converter.MeteoriteToMeteoriteDtoConverter;

import org.hibernate.validator.internal.util.stereotypes.Lazy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class LoanToLoanDtoConverter implements Converter<Loan, LoanDto> {

    @Autowired
    @Lazy
    private final MeteoriteToMeteoriteDtoConverter meteoriteToMeteoriteDtoConverter;

    public LoanToLoanDtoConverter(MeteoriteToMeteoriteDtoConverter meteoriteToMeteoriteDtoConverter) {
        this.meteoriteToMeteoriteDtoConverter = meteoriteToMeteoriteDtoConverter;
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

            source.getMeteorites() != null
                ? this.meteoriteToMeteoriteDtoConverter.convertList(source.getMeteorites()) : null,
            source.getNotes(),
            source.getExtraFiles()
        );
        return loanDto;
    }
}
