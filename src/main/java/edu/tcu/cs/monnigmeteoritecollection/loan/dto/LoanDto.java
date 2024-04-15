package edu.tcu.cs.monnigmeteoritecollection.loan.dto;

import edu.tcu.cs.monnigmeteoritecollection.meteorite.Meteorite;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

public record LoanDto(Integer id,
                               String name,
                               String institution,
                               String email,
                               String phone,
                               String address,
                               String loanStartDate,
                               String loanDueDate,
                      Integer numberOfMeteorites,
                      String notes,
 String extraFiles) {

}
