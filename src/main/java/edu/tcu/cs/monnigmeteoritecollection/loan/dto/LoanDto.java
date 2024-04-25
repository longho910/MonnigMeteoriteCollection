package edu.tcu.cs.monnigmeteoritecollection.loan.dto;


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

    Integer numberOfMeteorites,
    String notes,
    String extraFiles
) {

}
