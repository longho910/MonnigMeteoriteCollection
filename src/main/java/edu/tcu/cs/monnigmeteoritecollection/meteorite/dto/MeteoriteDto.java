package edu.tcu.cs.monnigmeteoritecollection.meteorite.dto;

import edu.tcu.cs.monnigmeteoritecollection.loan.Loan;
import edu.tcu.cs.monnigmeteoritecollection.loan.dto.LoanDto;

import java.math.BigDecimal;

public record MeteoriteDto(
        Long id,

        String name,

        String monnigNumber,
        String country,
        String _class,
        String group,
        Integer yearFound,
        BigDecimal weight,

        String howFound,
        LoanDto loan) {

}
