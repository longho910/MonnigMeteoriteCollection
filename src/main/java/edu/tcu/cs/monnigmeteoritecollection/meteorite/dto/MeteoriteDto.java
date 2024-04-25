package edu.tcu.cs.monnigmeteoritecollection.meteorite.dto;

import edu.tcu.cs.monnigmeteoritecollection.loan.dto.LoanDto;
import jakarta.validation.constraints.NotEmpty;

import java.math.BigDecimal;

public record MeteoriteDto(
        Long id,

        @NotEmpty(message = "name is required.")
        String name,
        @NotEmpty(message = "monnigNumber is required.")
        String monnigNumber,
        String country,
        String _class,
        String group,
        Integer yearFound,
        BigDecimal weight,

        String howFound,
        // List<SampleHistoryDto> sampleHistory,
        LoanDto loan
) {

}
