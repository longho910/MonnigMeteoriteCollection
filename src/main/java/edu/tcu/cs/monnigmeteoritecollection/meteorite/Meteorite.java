package edu.tcu.cs.monnigmeteoritecollection.meteorite;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
public class Meteorite implements Serializable {
    @Id
    private Long id;

    private String name;

    private String monnigNumber;
    private String country;
    private String _class;
    private String group;
    private Integer yearFound;
    private BigDecimal weight;

    public Meteorite() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
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
