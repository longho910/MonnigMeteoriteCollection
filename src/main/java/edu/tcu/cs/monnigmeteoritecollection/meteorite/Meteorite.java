package edu.tcu.cs.monnigmeteoritecollection.meteorite;

import edu.tcu.cs.monnigmeteoritecollection.loan.Loan;
import edu.tcu.cs.monnigmeteoritecollection.samplehistory.SampleHistory;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
public class Meteorite implements Serializable {

    @Id
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
    @OneToOne(mappedBy = "meteorite")
    private SampleHistory sampleHistory;
    @ManyToOne
    private Loan loan;


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

    public SampleHistory getSampleHistory() {
        return sampleHistory;
    }

    public void setSampleHistory(SampleHistory sampleHistory) {
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
