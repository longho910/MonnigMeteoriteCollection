package edu.tcu.cs.monnigmeteoritecollection.loan.dto;

import java.util.List;

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

    List<Long> meteorites,
    String notes,
    String extraFiles
) {

}
