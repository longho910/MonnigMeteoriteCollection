package edu.tcu.cs.monnigmeteoritecollection.meteorite;

import edu.tcu.cs.monnigmeteoritecollection.loan.Loan;
import edu.tcu.cs.monnigmeteoritecollection.samplehistory.SampleHistory;

import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Meteorite implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;            // internal ID used for queries

    // attributes given by sample data
    private String name;
    @Column(unique = true)
    private String monnigNumber;
    private String country;
    private String _class;
    private String _group;
    private Integer yearFound;
    private BigDecimal weight;

    // further attributes to assist with use cases
    private String howFound;

    
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "meteorite")
    private List<SampleHistory> sampleHistory = new ArrayList<>();

    @ManyToOne
    private Loan loan;   // store the loanId as opposed to entire Loan


    // constructor ----------------------------------------------------------------------------------------------------
    public Meteorite() {
    }

    // internal attributes getters and setters ------------------------------------------------------------------------
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getHowFound() {
        return howFound;
    }

    public void setHowFound(String howFound) {
        this.howFound = howFound;
    }

    public List<SampleHistory> getSampleHistory() {
        return sampleHistory;
    }

    public void setSampleHistory(List<SampleHistory> sampleHistory) {
        this.sampleHistory = sampleHistory;
    }

    // returns null if the Loan field is empty
    public Loan getLoan() {
        return loan;
    }

    public void setLoan(Loan loan) {
        this.loan = loan;
    }

    // given dataset getters and setters ------------------------------------------------------------------------------
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
