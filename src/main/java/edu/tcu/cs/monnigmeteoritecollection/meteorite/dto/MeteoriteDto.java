package edu.tcu.cs.monnigmeteoritecollection.meteorite.dto;

import edu.tcu.cs.monnigmeteoritecollection.loan.dto.LoanDto;
import edu.tcu.cs.monnigmeteoritecollection.samplehistory.dto.SampleHistoryDto;
import jakarta.validation.constraints.NotEmpty;

import java.math.BigDecimal;
import java.util.List;

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
        List<SampleHistoryDto> sampleHistory,
        LoanDto loan
) {

}
