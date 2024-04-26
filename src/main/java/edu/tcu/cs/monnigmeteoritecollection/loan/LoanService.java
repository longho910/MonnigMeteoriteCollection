package edu.tcu.cs.monnigmeteoritecollection.loan;
import java.util.List;
import org.springframework.stereotype.Service;

import edu.tcu.cs.monnigmeteoritecollection.meteorite.Meteorite;
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
        oldLoan.setName(update.getName());
        oldLoan.setInstitution(update.getInstitution());
        oldLoan.setEmail(update.getEmail());
        oldLoan.setPhone(update.getPhone());
        oldLoan.setAddress(update.getAddress());
        oldLoan.setLoanDueDate(update.getLoanDueDate());

        oldLoan.setMeteorites(update.getMeteorites());

        return this.loanRepository.save(oldLoan);
    }

    public void delete(Integer loanId) {
        this.loanRepository.deleteById(loanId);
    }
}