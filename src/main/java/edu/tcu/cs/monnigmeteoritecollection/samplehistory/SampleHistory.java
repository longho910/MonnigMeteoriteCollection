package edu.tcu.cs.monnigmeteoritecollection.samplehistory;

import jakarta.persistence.Id;

public class SampleHistory {
    @Id
    private Integer id;

    private String date;        // should be in DD-MM-YYYY
    private String category;    // should contain either CREATED, UPDATED, or ARCHIVED (if a meteorite is fully deleted from the system, its SampleHistory is also deleted)
    private String notes;


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
