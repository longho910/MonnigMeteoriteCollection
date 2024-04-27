package edu.tcu.cs.monnigmeteoritecollection.loan;
import java.util.List;
import org.springframework.stereotype.Service;

import edu.tcu.cs.monnigmeteoritecollection.system.exception.ObjectNotFoundException;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class LoanService {

    private final LoanRepository loanRepository;

    public LoanService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    public List<Loan> findAll() {
        return this.loanRepository.findAll();
    }

    public List<Loan> findAllNonArchived() {
        List<Loan> loanList = this.loanRepository.findAll();
        for (Loan elem : loanList) {
            // remove all ARCHIVED
            if (elem.isArchived()) {
                loanList.remove(elem);
            }
        }
        return loanList;
    }

    public List<Loan> findAllArchived() {
        List<Loan> loanList = this.loanRepository.findAll();
        for (Loan elem : loanList) {
            // remove all NON-ARCHIVED
            if (!(elem.isArchived())) {
                loanList.remove(elem);
            }
        }
        return loanList;
    }

    public Loan findById(String loanId) {
        return this.loanRepository.findById(Integer.valueOf(loanId))
                .orElseThrow(()->new ObjectNotFoundException("loan", String.valueOf(loanId)));
    }

    public Loan save(Loan loan) {
        return this.loanRepository.save(loan);
    }

    public Loan update(String loanId, Loan update) {
        Loan oldLoan = this.loanRepository.findById(Integer.valueOf(loanId))
            .orElseThrow(() -> new ObjectNotFoundException("loan", loanId));
        if (update.getName() != null) {
            oldLoan.setName(update.getName());
        }
        if (update.getInstitution() != null) {
            oldLoan.setInstitution(update.getInstitution());
        }
        if (update.getEmail() != null) {
            oldLoan.setEmail(update.getEmail());
        }
        if (update.getPhone() != null) {
            oldLoan.setPhone(update.getPhone());
        }
        if (update.getAddress() != null) {
            oldLoan.setAddress(update.getAddress());
        }
        if (update.getLoanDueDate() != null) {
            oldLoan.setLoanDueDate(update.getLoanDueDate());
        }
        if (update.getNotes() != null) {
            oldLoan.setNotes(update.getNotes());
        }
        if (update.getMeteorites() != null) {
            oldLoan.setMeteorites(update.getMeteorites());
        }

        return this.loanRepository.save(oldLoan);
    }

    public void delete(Integer loanId) {
        this.loanRepository.deleteById(loanId);
    }
}