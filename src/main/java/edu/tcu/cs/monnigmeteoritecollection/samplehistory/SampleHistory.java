package edu.tcu.cs.monnigmeteoritecollection.samplehistory;

import edu.tcu.cs.monnigmeteoritecollection.meteorite.Meteorite;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class SampleHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Meteorite meteorite;

    private String date;        // should be in DD-MM-YYYY
    private String category;    // should contain either CREATED, UPDATED, or ARCHIVED (if a meteorite is fully deleted from the system, its SampleHistory is also deleted)
    private String notes;


    public SampleHistory() {
    }

    // getters and setters -------------------------------------------------------------------------------------------------
    public Long getId() {
        return id;
    }

    public void setId(Long newId) {
        this.id = newId;
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

    public Meteorite getMeteorite() {
        return meteorite;
    }

    public void setMeteorite(Meteorite meteorite) {
        this.meteorite = meteorite;
    }
}
