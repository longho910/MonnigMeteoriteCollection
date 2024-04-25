package edu.tcu.cs.monnigmeteoritecollection.samplehistory;

import edu.tcu.cs.monnigmeteoritecollection.meteorite.Meteorite;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class SampleHistory {

    @Id
    private Long id;

    private String date;        // should be in DD-MM-YYYY
    private String category;    // should contain either CREATED, UPDATED, or ARCHIVED (if a meteorite is fully deleted from the system, its SampleHistory is also deleted)
    private String notes;

    @OneToOne(cascade = CascadeType.ALL)
    private Meteorite meteorite;


    public SampleHistory(String date, String category, String notes) {
        this.date = date;
        this.category = category;
        this.notes = notes;
    }

    // getters and setters, these will likely go unused ----------------------------------------------------------------------------
    public String getDate() {
        return date;
    }

    public String getCategory() {
        return category;
    }

    public String getNotes() {
        return notes;
    }
}
