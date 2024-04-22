package edu.tcu.cs.monnigmeteoritecollection.loan;

import java.util.ArrayList;
import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.tcu.cs.monnigmeteoritecollection.loan.converter.*;
import edu.tcu.cs.monnigmeteoritecollection.system.Result;
import edu.tcu.cs.monnigmeteoritecollection.system.StatusCode;
import edu.tcu.cs.monnigmeteoritecollection.loan.dto.LoanDto;


@RestController
@RequestMapping("${api.endpoint.base-url}/loans")
public class LoanController {
    private final LoanService loanService;
    private final LoanRepository loanRepository;
    private final LoanToLoanDtoConverter loanToLoanDtoConverter;
    private final LoanDtoToLoanConverter loanDtoToLoanConverter;

    public LoanController(LoanService loanService, LoanToLoanDtoConverter loanToLoanDtoConverter, LoanDtoToLoanConverter loanDtoToLoanConverter, LoanRepository loanRepository) {
        this.loanService = loanService;
        this.loanRepository = loanRepository;
        this.loanDtoToLoanConverter = loanDtoToLoanConverter;
        this.loanToLoanDtoConverter = loanToLoanDtoConverter;
    }

    // find one loan by ID and return
    @GetMapping("/{loanId}")
    public Result findLoanById(@PathVariable Integer loanId) {

        Loan foundLoan = this.loanService.findById(loanId);
        LoanDto loanDto = this.loanToLoanDtoConverter.convert(foundLoan);
        return new Result(true, StatusCode.SUCCESS, "Find One Loan Success", loanDto);
    }

    // return all loans
    @GetMapping("/api/v1/loan")
    public Result findAllLoans() {
        List<Loan> foundLoans = this.loanService.findAll();

        List<LoanDto> loanDtoList = new ArrayList<>();
        for (Loan loan : foundLoans) {
            loanDtoList.add(this.loanToLoanDtoConverter.convert(loan));
        }

        return new Result(true, StatusCode.SUCCESS, "Find All Loans Success", loanDtoList);
    }

    // update loan
    @PutMapping("/{loanId}")
    public Result updateLoan(@PathVariable Integer loanId, @Validated @RequestBody LoanDto loanDto) {
        Loan update = this.loanDtoToLoanConverter.convert(loanDto);
        Loan updatedLoan = this.loanService.update(loanId, update);
        LoanDto updatedLoanDto = this.loanToLoanDtoConverter.convert(updatedLoan);

        return new Result(true, StatusCode.SUCCESS, "Loan Update Success", updatedLoanDto);
    }


}
