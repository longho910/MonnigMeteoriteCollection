package edu.tcu.cs.monnigmeteoritecollection.loan;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
        if (!(update.getMeteorites().isEmpty())) {
            oldLoan.setMeteorites(update.getMeteorites());
        }
        if (update.isArchived() != null) {
            oldLoan.setArchived(update.isArchived());
        }

        return this.loanRepository.save(oldLoan);
    }

    public void delete(Integer loanId) {
        this.loanRepository.deleteById(loanId);
    }

    // add paging ability - enhanced search
    public Page<Loan> findByCriteria(Map<String, String> searchCriteria, Pageable pageable) {
        Specification<Loan> spec = Specification.where(null);

        if (StringUtils.hasLength(searchCriteria.get("name"))) {
            spec = spec.and(LoanSpecs.containsName(searchCriteria.get("name")));
        }

        if (StringUtils.hasLength(searchCriteria.get("institution"))) {
            spec = spec.and(LoanSpecs.containsInstitution(searchCriteria.get("institution")));
        }


        if (StringUtils.hasLength(searchCriteria.get("email"))) {
            spec = spec.and(LoanSpecs.containsEmail(searchCriteria.get("email")));
        }


        if (StringUtils.hasLength(searchCriteria.get("phone"))) {
            spec = spec.and(LoanSpecs.containsPhone(searchCriteria.get("phone")));
        }

        if (StringUtils.hasLength(searchCriteria.get("address"))) {
            spec = spec.and(LoanSpecs.containsAddress(searchCriteria.get("address")));
        }

        if (StringUtils.hasLength(searchCriteria.get("isArchived"))) {
            String providedStatus = searchCriteria.get("isArchived");
            if (providedStatus.equals("true")) {
                spec = spec.and(LoanSpecs.isArchived(true));
            } else {
                spec = spec.and(LoanSpecs.isArchived(false));
            }

        }

        return this.loanRepository.findAll(spec, pageable);
    }

}