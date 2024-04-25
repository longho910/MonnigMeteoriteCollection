package edu.tcu.cs.monnigmeteoritecollection.meteorite;

import edu.tcu.cs.monnigmeteoritecollection.loan.Loan;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
public class Meteorite implements Serializable {
    @Id
    private Long id;
    private Integer loanId;

    private String name;

    private String monnigNumber;
    private String country;
    private String _class;
    private String _group;
    private Integer yearFound;
    private BigDecimal weight;

    private String howFound;

    public String getHowFound() {
        return howFound;
    }

    public Loan getLoan() {
        return loan;
    }

    public void setLoan(Loan loan) {
        this.loan = loan;
    }

    @ManyToOne
    private Loan loan;

    public void setHowFound(String howFound) {
        this.howFound = howFound;
    }

    public Meteorite() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getLoanId() {
        return loanId;
    }

    public void setLoanId(Integer loanId) {
        this.loanId = loanId;
    }

    // rather than using setLoanId(null), I thought this would be clearer in other classes
    public void wipeLoanId() {
        this.loanId = null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMonnigNumber() {
        return monnigNumber;
    }

    public void setMonnigNumber(String monnigNumber) {
        this.monnigNumber = monnigNumber;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String get_class() {
        return _class;
    }

    public void set_class(String _class) {
        this._class = _class;
    }

    public String get_group() {
        return _group;
    }

    public void set_group(String group) {
        this._group = group;
    }

    public Integer getYearFound() {
        return yearFound;
    }

    public void setYearFound(Integer yearFound) {
        this.yearFound = yearFound;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }
}
