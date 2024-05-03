package edu.tcu.cs.monnigmeteoritecollection.meteorite.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.List;

public record MeteoriteDto(
        Long id,

        @NotEmpty(message = "name is required.")
        String name,
        @NotEmpty(message = "monnigNumber is required.")
        String monnigNumber,
        @NotEmpty(message = "country is required.")
        String country,
        @NotEmpty(message = "_class is required.")
        String _class,
        @NotEmpty(message = "group is required.")
        String group,
        @NotNull(message = "yearFound is required.")
        Integer yearFound,
        @NotNull(message = "weight is required.")
        BigDecimal weight,
        @NotEmpty(message = "howFound is required.")
        String howFound,
        @NotNull(message = "sampleHistory is required.")
        List<Long> sampleHistories,
        @NotNull(message = "loan is required.")
        Integer loan
) {

}
