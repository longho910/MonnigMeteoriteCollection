package edu.tcu.cs.monnigmeteoritecollection.loan;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.tcu.cs.monnigmeteoritecollection.loan.converter.*;
import edu.tcu.cs.monnigmeteoritecollection.system.Result;
import edu.tcu.cs.monnigmeteoritecollection.system.StatusCode;
import jakarta.validation.Valid;
import edu.tcu.cs.monnigmeteoritecollection.loan.dto.LoanDto;


@RestController
@RequestMapping("${api.endpoint.base-url}/loans")
@CrossOrigin(origins = "*") // Allow requests from any origin
public class LoanController {
    private final LoanService loanService;
    private final LoanToLoanDtoConverter loanToLoanDtoConverter;
    private final LoanDtoToLoanConverter loanDtoToLoanConverter;

    public LoanController(LoanService loanService, LoanToLoanDtoConverter loanToLoanDtoConverter, LoanDtoToLoanConverter loanDtoToLoanConverter) {
        this.loanService = loanService;
        this.loanDtoToLoanConverter = loanDtoToLoanConverter;
        this.loanToLoanDtoConverter = loanToLoanDtoConverter;
    }

    // UC-11. UC-12
    @GetMapping("/{loanId}")
    public Result findLoanById(@PathVariable String loanId) {

        Loan foundLoan = this.loanService.findById(loanId);
        LoanDto loanDto = this.loanToLoanDtoConverter.convert(foundLoan);
        return new Result(true, StatusCode.SUCCESS, "Find One Loan Success", loanDto);
    }

    // UC-11, UC-12
    @GetMapping
    public Result findAllLoans() {
        List<Loan> foundLoans = this.loanService.findAll();

        List<LoanDto> loanDtoPage = new ArrayList<>();
        for (Loan elem : foundLoans) {
            loanDtoPage.add(this.loanToLoanDtoConverter.convert(elem));
        }

        return new Result(true, StatusCode.SUCCESS, "Find All Loans Success", loanDtoPage);
    }

    // UC-11, UC-12
    // returns non-archived loans
    @GetMapping("/nonarchived")
    public Result findAllNonArchived() {
        List<Loan> foundLoans = this.loanService.findAllNonArchived();

        List<LoanDto> loanDtoList = new ArrayList<>();
        for (Loan elem : foundLoans) {
            loanDtoList.add(this.loanToLoanDtoConverter.convert(elem));
        }

        return new Result(true, StatusCode.SUCCESS, "Find All Non-Archived Loans Success", loanDtoList);
    }

    // UC-11, UC-12
    // return archived loans
    @GetMapping("/archived")
    public Result findAllArchived() {
        List<Loan> foundLoans = this.loanService.findAllArchived();

        List<LoanDto> loanDtoList = new ArrayList<>();
        for (Loan elem : foundLoans) {
            loanDtoList.add(this.loanToLoanDtoConverter.convert(elem));
        }

        return new Result(true, StatusCode.SUCCESS, "Find All Non-Archived Loans Success", loanDtoList);
    }

    // UC-14
    // create loan, returns a copy of the saved loan in the SUCCESS Result
    @PostMapping
    public Result addLoan(@Valid @RequestBody LoanDto loanDto) {
        // convert loanDto to loan
        Loan newLoan = this.loanDtoToLoanConverter.convert(loanDto);
        Loan savedLoan = this.loanService.save(newLoan);

        LoanDto savedLoanDto = this.loanToLoanDtoConverter.convert(savedLoan);

        return new Result(true, StatusCode.SUCCESS, "Add Success", savedLoanDto);
    }

    // UC-13, UC-15
    // update loan -- this method also handles ARCHIVE LOAN (by sending an empty loanDto with only isArchived changed)
    // also use this method to add Meteorites to a given Loan
    @PutMapping
    public Result updateLoan(@Validated @RequestBody LoanDto loanDto) {
        Loan update = this.loanDtoToLoanConverter.convert(loanDto);
        String loanId = Integer.toString(loanDto.id());
        Loan updatedLoan = this.loanService.update(loanId, update);
        LoanDto updatedLoanDto = this.loanToLoanDtoConverter.convert(updatedLoan);

        return new Result(true, StatusCode.SUCCESS, "Loan Update Success", updatedLoanDto);
    }

    // this method should not find archived loans by default
    @PostMapping("/search")
    public Result findLoansByCriteria(@RequestBody Map<String, String> searchCriteria, Pageable pageable) {
        Page<Loan> loanPage = this.loanService.findByCriteria(searchCriteria, pageable);
        Page<LoanDto> loanDtoPage = loanPage.map(this.loanToLoanDtoConverter::convert);

        return new Result(true, StatusCode.SUCCESS, "Search Success", loanDtoPage);
    }


}
