package edu.tcu.cs.monnigmeteoritecollection.loan;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import edu.tcu.cs.monnigmeteoritecollection.meteorite.Meteorite;

@Entity
public class Loan implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String institution;
    private String email;
    private String phone;
    private String address;
    private String loanStartDate;       // require format MM-DD-YYYY
    private String loanDueDate;         // require format MM-DD-YYYY

    // boolean to track archival
    private Boolean isArchived;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy =  "loan")
    private List<Meteorite> meteorites = new ArrayList<>();

    private String notes;
    private String extraFiles;

    public Loan() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLoanStartDate() {
        return loanStartDate;
    }

    public void setLoanStartDate(String loanStartDate) {
        this.loanStartDate = loanStartDate;
    }

    public String getLoanDueDate() {
        return loanDueDate;
    }

    public void setLoanDueDate(String loanDueDate) {
        this.loanDueDate = loanDueDate;
    }

    public List<Meteorite> getMeteorites() {
        return meteorites;
    }

    public void addMeteorite(Meteorite meteorite) {
        meteorites.add(meteorite);
        meteorite.setLoan(this);
    }

    public void removeMeteorite(Meteorite meteorite) {
        meteorites.remove(meteorite);
        meteorite.setLoan(null);
    }

    public void setMeteorites(List<Meteorite> updateList) {
        // first remove all current meteorites from the Loan
        clearMeteorites();

        // now add back all meteorites found in the List passed originally
        for (Meteorite elem : updateList) {
            addMeteorite(elem);
        }
    }
    private void clearMeteorites() {
        List<Meteorite> emptyList = new ArrayList<>();
        this.meteorites = emptyList;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getExtraFiles() {
        return extraFiles;
    }

    public void setExtraFiles(String extraFiles) {
        this.extraFiles = extraFiles;
    }

    public Integer getNumberOfMeteorites() {
        return this.meteorites.size();
    }

    public Boolean isArchived() {
        return this.isArchived;
    }

    public Boolean setArchived(Boolean newValue) {
        this.isArchived = newValue;
        return this.isArchived;
    }
}
