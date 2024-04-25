package edu.tcu.cs.monnigmeteoritecollection.samplehistory;

import edu.tcu.cs.monnigmeteoritecollection.meteorite.Meteorite;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;

public class SampleHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    @MapsId
    private Meteorite meteorite;

    private String date;        // should be in DD-MM-YYYY
    private String category;    // should contain either CREATED, UPDATED, or ARCHIVED (if a meteorite is fully deleted from the system, its SampleHistory is also deleted)
    private String notes;


    public SampleHistory(String date, String category, String notes) {
        this.date = date;
        this.category = category;
        this.notes = notes;
    }

    // getters and setters -------------------------------------------------------------------------------------------------
    public Long getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String newDate) {
        this.date = newDate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String newCategory) {
        this.category = newCategory;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String newNotes) {
        this.notes = newNotes;
    }

    public Long getMeteoriteId() {
        return meteorite.getId();
    }

    public void setMeteorite(Meteorite meteorite) {
        this.meteorite = meteorite;
    }
}
