package edu.tcu.cs.monnigmeteoritecollection.loan.dto;

import java.util.List;
import edu.tcu.cs.monnigmeteoritecollection.meteorite.dto.MeteoriteDto;;;

public record LoanDto(
    Integer id,
    String name,
    String institution,
    String email,
    String phone,
    String address,
    String loanStartDate,
    String loanDueDate,

    Boolean isArchived,

    List<MeteoriteDto> meteoriteDtoList,
    String notes,
    String extraFiles
) {

}
